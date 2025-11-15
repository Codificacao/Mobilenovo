package com.example.nossotcc.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.nossotcc.R;

public class Pagina01 extends BaseActivity {

    Button btnLogin, btnCadastro, btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina01);

        btnLogin    = findViewById(R.id.btnLogin);
        btnCadastro = findViewById(R.id.btnCadastro);
        btnSair     = findViewById(R.id.btnSair);

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(Pagina01.this, Login.class);
            startActivity(intent);
        });

        btnCadastro.setOnClickListener(v -> {
            Intent intent = new Intent(Pagina01.this, Cadastro.class);
            startActivity(intent);
        });

        btnSair.setOnClickListener(v -> {
            finish();
        });
    }
}
