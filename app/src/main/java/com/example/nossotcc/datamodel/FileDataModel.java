package com.example.nossotcc.datamodel;

public class FileDataModel {

    public static final String TABELA = "file_item";

    public static final String ID = "id";
    public static final String ID_CATEGORIA = "id_categoria";
    public static final String CAMINHO = "caminho_arquivo";

    public static String criarTabela() {
        return "CREATE TABLE " + TABELA + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ID_CATEGORIA + " INTEGER NOT NULL, " +
                CAMINHO + " TEXT NOT NULL, " +
                "FOREIGN KEY(" + ID_CATEGORIA + ") REFERENCES categoria(id)" +
                ");";
    }
}
