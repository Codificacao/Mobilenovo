package com.example.nossotcc.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nossotcc.R;
import com.example.nossotcc.controller.PinManager;


public class CriarPinActivity extends AppCompatActivity {

    private EditText edtPin1, edtPin2;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_pin);

        edtPin1 = findViewById(R.id.edtPin1);
        edtPin2 = findViewById(R.id.edtPin2);
        btnSalvar = findViewById(R.id.btnSalvarPin);

        btnSalvar.setOnClickListener(v -> {
            String p1 = edtPin1.getText().toString();
            String p2 = edtPin2.getText().toString();

            if (p1.length() < 4) {
                Toast.makeText(this, "O PIN deve ter pelo menos 4 dígitos!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!p1.equals(p2)) {
                Toast.makeText(this, "Os PINs não coincidem!", Toast.LENGTH_SHORT).show();
                return;
            }

            PinManager.salvarPin(this, p1);
            Toast.makeText(this, "PIN criado com sucesso!", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, Documentos.class));
            finish();
        });
    }
}
