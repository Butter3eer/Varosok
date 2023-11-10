package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    private EditText editTextInsertNev;
    private EditText editTextInsertOrszag;
    private EditText editTextInsertLakossag;
    private Button buttonInsertFelvetel;
    private Button buttonInsertVissza;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();

        buttonInsertVissza.setOnClickListener(view -> {
            Intent intent = new Intent(InsertActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        buttonInsertFelvetel.setOnClickListener(view -> {
            if (editTextInsertNev.getText().toString().isEmpty() || editTextInsertOrszag.getText().toString().isEmpty() || editTextInsertLakossag.getText().toString().isEmpty()) {
                Toast.makeText(this, "Minden mező kitöltése kötelező!", Toast.LENGTH_SHORT).show();
                buttonInsertFelvetel.setClickable(false);
            }
            else {
                buttonInsertFelvetel.setClickable(true);
                if (dbHelper.rogzites(editTextInsertNev.getText().toString(), editTextInsertOrszag.getText().toString(), Integer.parseInt(editTextInsertLakossag.getText().toString()))) {
                    Toast.makeText(this, "Sikeres felvétel!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "Sikertelen adat felvétel!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        editTextInsertNev.setOnFocusChangeListener((view, b) -> {
            buttonInsertFelvetel.setClickable(false);
            if (!b) {
                if (dbHelper.adatLekerdezes(editTextInsertNev.getText().toString()).getCount() == 0) {
                    editTextInsertNev.setTextColor(getResources().getColor(R.color.Green));
                    buttonInsertFelvetel.setClickable(true);
                }
                else {
                    editTextInsertNev.setTextColor(getResources().getColor(R.color.Red));
                }
            }
            else {
                editTextInsertNev.setTextColor(getResources().getColor(R.color.black));
            }
        });
    }

    public void init() {
        editTextInsertNev = findViewById(R.id.editTextInsertNev);
        editTextInsertOrszag = findViewById(R.id.editTextInsertOrszag);
        editTextInsertLakossag = findViewById(R.id.editTextInsertLakossag);
        buttonInsertFelvetel = findViewById(R.id.buttonInsertFelvetel);
        buttonInsertVissza = findViewById(R.id.buttonInsertVissza);
        dbHelper = new DBHelper(this);
    }
}