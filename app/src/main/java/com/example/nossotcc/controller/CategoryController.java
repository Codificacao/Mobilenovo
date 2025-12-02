package com.example.nossotcc.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.nossotcc.database.AppDataBase;
import com.example.nossotcc.model.Category;

import java.util.ArrayList;

public class CategoryController extends AppDataBase {

    public CategoryController(Context context) {
        super(context);
    }

    public boolean inserirCategoria(Category category) {
        ContentValues values = new ContentValues();
        values.put("nome", category.getNome());
        return insert("category", values);
    }

    public ArrayList<Category> listarCategorias() {
        ArrayList<Category> lista = new ArrayList<>();

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM category", null);
        if (cursor.moveToFirst()) {
            do {
                Category c = new Category();
                c.setId(cursor.getInt(0));
                c.setNome(cursor.getString(1));
                lista.add(c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }
}


