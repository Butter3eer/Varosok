package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextMainOrszag;
    private Button buttonMainKeres;
    private Button buttonMainUj;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        buttonMainUj.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, InsertActivity.class);
            startActivity(intent);
            finish();
        });

        buttonMainKeres.setOnClickListener(view -> {
            if (editTextMainOrszag.getText().toString().isEmpty()) {
                Toast.makeText(this, "A mező nem lehet üres!", Toast.LENGTH_SHORT).show();
            }
            else {
                SharedPreferences sharedPref = getSharedPreferences("SharedPreference", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("kereses", editTextMainOrszag.getText().toString());
                editor.apply();

                Intent intent = new Intent(MainActivity.this, SearchResultActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init() {
        editTextMainOrszag = findViewById(R.id.editTextMainOrszag);
        buttonMainKeres = findViewById(R.id.buttonMainKeres);
        buttonMainUj = findViewById(R.id.buttonMainUj);
        dbHelper = new DBHelper(this);
    }
}