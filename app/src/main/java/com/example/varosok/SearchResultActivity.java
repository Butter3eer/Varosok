package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SearchResultActivity extends AppCompatActivity {

    private TextView textViewSearchVarosok;
    private Button buttonSearchVissza;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        init();

        buttonSearchVissza.setOnClickListener(view -> {
            Intent intent = new Intent(SearchResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        SharedPreferences sharedPref = getSharedPreferences("SharedPreference", Context.MODE_PRIVATE);
        String kereses = sharedPref.getString("kereses", "");
        Cursor eredmeny = dbHelper.adatLekerdezes(kereses);
        if (eredmeny.getCount() == 0) {
            textViewSearchVarosok.setText("Nem található rekord a következő adattal:" + kereses);
        }
        else {
            while (eredmeny.moveToNext()) {
                textViewSearchVarosok.append(eredmeny.getString(0) + "\n");
            }
        }
    }

    public void init() {
        textViewSearchVarosok = findViewById(R.id.textViewSearchVarosok);
        buttonSearchVissza = findViewById(R.id.buttonSearchVissza);
        dbHelper = new DBHelper(this);
    }
}