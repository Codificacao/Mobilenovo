package com.example.nossotcc.model;

public class Folder {

    private int id;
    private String name;
    private String data;

    public Folder(String name) {
        this.name = name;
        this.data = String.valueOf(System.currentTimeMillis());
    }

    public Folder(int id, String name, String data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getData() { return data; }
}
