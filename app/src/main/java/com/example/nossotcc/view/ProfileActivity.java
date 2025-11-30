package com.example.nossotcc.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.nossotcc.R;
import com.example.nossotcc.controller.UsuarioController;
import com.example.nossotcc.model.Usuario;

public class ProfileActivity extends AppCompatActivity {

    TextView txtNome, txtEmail, txtData, txtNacionalidade, txtGenero;
    UsuarioController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtNome = findViewById(R.id.txtNome);
        txtEmail = findViewById(R.id.txtEmail);
        txtData = findViewById(R.id.txtDataNascimento);
        txtNacionalidade = findViewById(R.id.txtNacionalidade);
        txtGenero = findViewById(R.id.txtGenero);

        controller = new UsuarioController(this);

        int userId = getIntent().getIntExtra("USER_ID", -1);

        if (userId != -1) {
            Usuario usuario = controller.buscar(userId);

            if (usuario != null) {
                txtNome.setText(usuario.getUserNome());
                txtEmail.setText(usuario.getUserEmail());
                txtData.setText(usuario.getNascimento());
                txtNacionalidade.setText(usuario.getNacionalidade());
                txtGenero.setText(usuario.getGenero());
            }
        }
    }
}
