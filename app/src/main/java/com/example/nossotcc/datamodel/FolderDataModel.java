package com.example.nossotcc.datamodel;

public class FolderDataModel {

    public static final String TABELA = "folder";

    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String DATA = "data"; // <-- AGORA EXISTE

    public static String criarTabela() {
        return "CREATE TABLE IF NOT EXISTS " + TABELA + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NOME + " TEXT NOT NULL, "
                + DATA + " TEXT"  // <-- ADICIONADO AQUI
                + ")";
    }
}
