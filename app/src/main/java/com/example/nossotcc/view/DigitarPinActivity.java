package com.example.nossotcc.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nossotcc.R;
import com.example.nossotcc.controller.PinManager;


public class DigitarPinActivity extends AppCompatActivity {

    private EditText edtPin;
    private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digitar_pin);

        edtPin = findViewById(R.id.edtPin);
        btnEntrar = findViewById(R.id.btnEntrarPin);

        btnEntrar.setOnClickListener(v -> {
            String pin = edtPin.getText().toString();

            if (PinManager.validarPin(this, pin)) {
                startActivity(new Intent(this, Documentos.class));
                finish();
            } else {
                Toast.makeText(this, "PIN incorreto!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
