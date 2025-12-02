package com.example.nossotcc.view;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nossotcc.R;

public class ExibirFotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_foto);

        ImageView img = findViewById(R.id.imgTelaCheia);

        String caminho = getIntent().getStringExtra("caminho");

        if (caminho != null) {
            img.setImageURI(Uri.parse(caminho));
        }
    }
}
