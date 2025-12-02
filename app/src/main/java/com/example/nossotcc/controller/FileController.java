package com.example.nossotcc.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.nossotcc.database.AppDataBase;
import com.example.nossotcc.model.FileItem;

import java.util.ArrayList;

public class FileController extends AppDataBase {

    public FileController(Context context) {
        super(context);
    }


    public boolean inserirArquivo(FileItem item) {
        ContentValues values = new ContentValues();
        values.put("id_categoria", item.getIdCategoria());
        values.put("caminho_arquivo", item.getCaminho());
        return insert("file_item", values);
    }


    public ArrayList<FileItem> listarArquivos(int idCategoria) {
        ArrayList<FileItem> lista = new ArrayList<>();

        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM file_item WHERE id_categoria = ?",
                new String[]{String.valueOf(idCategoria)}
        );

        if (cursor.moveToFirst()) {
            do {
                FileItem f = new FileItem();
                f.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                f.setIdCategoria(cursor.getInt(cursor.getColumnIndexOrThrow("id_categoria")));
                f.setCaminho(cursor.getString(cursor.getColumnIndexOrThrow("caminho_arquivo")));
                lista.add(f);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return lista;
    }


    public boolean excluirArquivo(int id) {
        return getWritableDatabase().delete(
                "file_item",
                "id = ?",
                new String[]{String.valueOf(id)}
        ) > 0;
    }
}
