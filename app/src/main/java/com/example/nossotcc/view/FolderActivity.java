package com.example.nossotcc.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nossotcc.R;
import com.example.nossotcc.controller.FileController;
import com.example.nossotcc.model.FileItem;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class FolderActivity extends AppCompatActivity {

    private ImageButton btnVoltar, btnAddFoto;
    private TextView txtNome;
    private GridLayout gridFotos;

    private FileController fileController;
    private int idCategoria;
    private String folderName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);

        folderName = getIntent().getStringExtra("folderName");
        idCategoria = folderName.hashCode();

        fileController = new FileController(this);

        btnVoltar = findViewById(R.id.btnVoltar);
        btnAddFoto = findViewById(R.id.btnAdd);
        txtNome = findViewById(R.id.txtNomePasta);
        gridFotos = findViewById(R.id.gridFotos);

        txtNome.setText(folderName);

        btnVoltar.setOnClickListener(v -> finish());


        ActivityResultLauncher<String> selecionarImagem =
                registerForActivityResult(new ActivityResultContracts.GetContent(),
                        uri -> {
                            if (uri != null) {


                                String caminhoLocal = salvarImagemLocal(uri);

                                if (caminhoLocal != null) {
                                    FileItem item = new FileItem();
                                    item.setIdCategoria(idCategoria);
                                    item.setCaminho(caminhoLocal);

                                    fileController.inserirArquivo(item);
                                    carregarArquivos();
                                }
                            }
                        });

        btnAddFoto.setOnClickListener(v -> selecionarImagem.launch("image/*"));

        carregarArquivos();
    }


    private void carregarArquivos() {
        gridFotos.removeAllViews();

        ArrayList<FileItem> lista = fileController.listarArquivos(idCategoria);

        for (FileItem item : lista) {
            ImageView img = new ImageView(this);
            img.setLayoutParams(new ViewGroup.LayoutParams(300, 300));
            img.setPadding(16, 16, 16, 16);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);

            Bitmap bitmap = BitmapFactory.decodeFile(item.getCaminho());
            img.setImageBitmap(bitmap);

            img.setOnClickListener(v -> {
                Intent intent = new Intent(FolderActivity.this, ExibirFotoActivity.class);
                intent.putExtra("caminho", item.getCaminho());
                startActivity(intent);
            });
            img.setOnLongClickListener(v -> {
                new androidx.appcompat.app.AlertDialog.Builder(FolderActivity.this)
                        .setTitle("Excluir foto")
                        .setMessage("Deseja excluir essa foto?")
                        .setPositiveButton("Excluir", (dialog, which) -> {
                            fileController.excluirArquivo(item.getId());
                            carregarArquivos();
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();

                return true;
            });

            gridFotos.addView(img);
        }
    }


    private String salvarImagemLocal(Uri uri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            String fileName = "foto_" + System.currentTimeMillis() + ".jpg";

            File file = new File(getFilesDir(), fileName);
            FileOutputStream out = new FileOutputStream(file);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
