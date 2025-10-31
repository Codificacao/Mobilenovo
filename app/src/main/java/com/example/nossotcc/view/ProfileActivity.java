package com.example.nossotcc.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nossotcc.R;

public class ProfileActivity extends BaseActivity {

    private ImageView imgProfile;
    private TextView tvNome, tvEmail, tvDataNasc, tvNacionalidade, tvGenero;
    private Button btnEditarPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        imgProfile = findViewById(R.id.imgProfile);
//        tvNome = findViewById(R.id.tvNome);
//        tvEmail = findViewById(R.id.tvEmail);
//        tvDataNasc = findViewById(R.id.tvDataNasc);
//        tvNacionalidade = findViewById(R.id.tvNacionalidade);
//        tvGenero = findViewById(R.id.tvGenero);
//        btnEditarPerfil = findViewById(R.id.btnEditarPerfil);


        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        tvNome.setText("Nome: " + prefs.getString("nomeUsuario", ""));
        tvEmail.setText("Email: " + prefs.getString("emailUsuario", ""));
        tvDataNasc.setText("Data de Nascimento: " + prefs.getString("dataNasc", ""));
        tvNacionalidade.setText("Nacionalidade: " + prefs.getString("nacionalidade", ""));
        tvGenero.setText("Gênero: " + prefs.getString("genero", ""));


        btnEditarPerfil.setOnClickListener(view -> {
            Intent intent = new Intent(ProfileActivity.this, Cadastro.class);
            startActivity(intent);
        });
    }
}
