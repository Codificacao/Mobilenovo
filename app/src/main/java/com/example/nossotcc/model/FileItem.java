package com.example.nossotcc.model;

public class FileItem {
    private int id;
    private int idCategoria;
    private String caminho; // URI da foto

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

    public String getCaminho() { return caminho; }
    public void setCaminho(String caminho) { this.caminho = caminho; }
}


