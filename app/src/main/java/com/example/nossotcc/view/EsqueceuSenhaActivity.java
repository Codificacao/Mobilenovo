package com.example.nossotcc.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.example.nossotcc.R;
import com.google.firebase.auth.FirebaseAuth;

public class EsqueceuSenhaActivity extends BaseActivity {

    private EditText emailRecuperacao;
    private Button btnRecuperar, btnVoltar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu_senha);

        emailRecuperacao = findViewById(R.id.email_recuperacao);
        btnRecuperar = findViewById(R.id.btn_recuperar);
        btnVoltar = findViewById(R.id.btn_voltar_login);

        mAuth = FirebaseAuth.getInstance();


        btnVoltar.setOnClickListener(v -> {
            startActivity(new Intent(EsqueceuSenhaActivity.this, Login.class));
            finish();
        });


        btnRecuperar.setOnClickListener(v -> {
            String email = emailRecuperacao.getText().toString().trim();

            if (email.isEmpty()) {
                Toast.makeText(this, "Digite seu e-mail!", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "E-mail de redefinição enviado!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(this, Login.class));
                            finish();
                        } else {
                            Toast.makeText(this, "Erro: verifique o e-mail cadastrado.", Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }
}
