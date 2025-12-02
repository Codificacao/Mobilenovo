package com.example.nossotcc.model;

public class Arquivo {

    private int id;
    private int folderId;
    private String uri;
    private String tipo;
    private String data;

    public Arquivo(int folderId, String uri, String tipo) {
        this.folderId = folderId;
        this.uri = uri;
        this.tipo = tipo;
        this.data = String.valueOf(System.currentTimeMillis());
    }

    public Arquivo(int id, int folderId, String uri, String tipo, String data) {
        this.id = id;
        this.folderId = folderId;
        this.uri = uri;
        this.tipo = tipo;
        this.data = data;
    }

    public int getId() { return id; }
    public int getFolderId() { return folderId; }
    public String getUri() { return uri; }
    public String getTipo() { return tipo; }
    public String getData() { return data; }
}
