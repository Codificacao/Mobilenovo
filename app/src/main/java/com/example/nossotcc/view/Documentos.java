package com.example.nossotcc.view;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.nossotcc.R;
import com.example.nossotcc.adapter.FolderAdapter;
import com.example.nossotcc.model.Folder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Documentos extends AppCompatActivity {

    private ImageButton btnVoltar, btnAdd;
    private GridLayout gridFolders;

    private List<Folder> folderList = new ArrayList<>();
    private FolderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.documentos);

        btnVoltar = findViewById(R.id.btnVoltar);
        btnAdd = findViewById(R.id.btnAdd);
        gridFolders = findViewById(R.id.gridFolders);

        btnVoltar.setOnClickListener(v -> finish());

        loadFolders();

        adapter = new FolderAdapter(this, gridFolders, folderList, folder -> {
            Intent i = new Intent(Documentos.this, FolderActivity.class);
            i.putExtra("folderName", folder.getName());
            startActivity(i);
        });

        btnAdd.setOnClickListener(v -> showCreateFolderDialog());
    }

    private void showCreateFolderDialog() {
        EditText input = new EditText(this);
        input.setHint("Nome da pasta");

        new AlertDialog.Builder(this)
                .setTitle("Criar pasta")
                .setView(input)
                .setPositiveButton("Criar", (dialog, which) -> {
                    String name = input.getText().toString().trim();
                    if (!name.isEmpty()) {
                        folderList.add(new Folder(name));
                        saveFolders();
                        adapter.render();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void saveFolders() {
        SharedPreferences prefs = getSharedPreferences("folders", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Set<String> names = new HashSet<>();
        for (Folder f : folderList) names.add(f.getName());

        editor.putStringSet("folderList", names);
        editor.apply();
    }

    private void loadFolders() {
        SharedPreferences prefs = getSharedPreferences("folders", MODE_PRIVATE);
        Set<String> names = prefs.getStringSet("folderList", new HashSet<>());

        folderList.clear();
        for (String n : names) folderList.add(new Folder(n));
    }
}
