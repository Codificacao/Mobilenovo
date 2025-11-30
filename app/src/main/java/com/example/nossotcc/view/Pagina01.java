package com.example.nossotcc.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.nossotcc.R;

import java.util.Locale;

public class Pagina01 extends BaseActivity {

    Button btnLogin, btnCadastro;
    ImageButton btnIdioma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina01);

        btnLogin    = findViewById(R.id.btnLogin);
        btnCadastro = findViewById(R.id.btnCadastro);
        btnIdioma   = findViewById(R.id.Idioma);


        btnIdioma.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(Pagina01.this);
            builder.setTitle(getString(R.string.selecione_idioma));


            String[] idiomas = {
                    getString(R.string.idioma_pt),
                    getString(R.string.idioma_en),
                    getString(R.string.idioma_es),
                    getString(R.string.idioma_fr),
                    getString(R.string.idioma_ht),
                    getString(R.string.idioma_zh),
                    getString(R.string.idioma_hi)
            };

            builder.setItems(idiomas, (dialog, which) -> {
                switch (which) {
                    case 0: setLocale("pt"); break;
                    case 1: setLocale("en"); break;
                    case 2: setLocale("es"); break;
                    case 3: setLocale("fr"); break;
                    case 4: setLocale("ht"); break;
                    case 5: setLocale("zh"); break;
                    case 6: setLocale("hi"); break;
                }
            });

            builder.show();
        });


        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(Pagina01.this, Login.class);
            startActivity(intent);
        });


        btnCadastro.setOnClickListener(v -> {
            Intent intent = new Intent(Pagina01.this, Cadastro.class);
            startActivity(intent);
        });
    }


    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);

        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        recreate();
    }
}
