package com.example.nossotcc.datamodel;

public class CategoryDataModel {

    public static final String TABELA = "category";
    public static final String ID = "id";
    public static final String NOME = "nome";

    public static String criarTabela() {
        return "CREATE TABLE " + TABELA + " ( " +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME + " TEXT NOT NULL );";
    }
}
