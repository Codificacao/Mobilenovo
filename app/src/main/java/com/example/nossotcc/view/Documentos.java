package com.example.nossotcc.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.nossotcc.R;

public class Documentos extends AppCompatActivity {

    private static final int REQUEST_IMAGE_GALLERY = 100;
    private static final int REQUEST_IMAGE_CAMERA = 101;
    private static final int PERMISSION_REQUEST = 102;

    private ImageButton btnVoltar, btnAdd;
    private GridLayout gridFolders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.documentos);

        btnVoltar = findViewById(R.id.btnVoltar);
        btnAdd = findViewById(R.id.btnAdd);
        gridFolders = findViewById(R.id.gridFolders);


        btnVoltar.setOnClickListener(v -> finish());


        btnAdd.setOnClickListener(v -> showImageOptions());


        for (int i = 0; i < gridFolders.getChildCount(); i++) {
            ImageView folder = (ImageView) gridFolders.getChildAt(i);
            final int index = i;
            folder.setOnClickListener(v -> openFolder(index));
        }
    }

    private void showImageOptions() {

        String[] options = {"Galeria", "Câmera"};
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("Adicionar imagem")
                .setItems(options, (dialog, which) -> {
                    if (which == 0) {
                        pickImageFromGallery();
                    } else if (which == 1) {
                        takePhoto();
                    }
                })
                .show();
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_GALLERY);
    }

    private void takePhoto() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_IMAGE_CAMERA);
        }
    }

    private void openFolder(int index) {
        Toast.makeText(this, "Abrindo pasta " + (index + 1), Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_GALLERY && data != null) {
                Uri selectedImage = data.getData();
                Toast.makeText(this, "Imagem selecionada da galeria: " + selectedImage, Toast.LENGTH_SHORT).show();
            } else if (requestCode == REQUEST_IMAGE_CAMERA && data != null) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                Toast.makeText(this, "Foto capturada!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePhoto();
            } else {
                Toast.makeText(this, "Permissão de câmera negada", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
