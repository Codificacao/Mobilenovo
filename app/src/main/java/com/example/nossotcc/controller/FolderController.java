package com.example.nossotcc.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nossotcc.database.AppDataBase;
import com.example.nossotcc.datamodel.FolderDataModel;
import com.example.nossotcc.model.Folder;

import java.util.ArrayList;
import java.util.List;

public class FolderController extends AppDataBase {

    public FolderController(Context context) {
        super(context);
    }

    public long salvar(Folder f) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(FolderDataModel.NOME, f.getName());
        valores.put(FolderDataModel.DATA, f.getData());

        long id = db.insert(FolderDataModel.TABELA, null, valores);
        db.close();
        return id;
    }

    public List<Folder> listar() {
        List<Folder> lista = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + FolderDataModel.TABELA, null);

        if (c.moveToFirst()) {
            do {
                Folder f = new Folder(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2)
                );
                lista.add(f);
            } while (c.moveToNext());
        }
        c.close();
        return lista;
    }
}
