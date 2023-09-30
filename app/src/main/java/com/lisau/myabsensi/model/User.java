package com.lisau.myabsensi.model;

public class User {
    private String id, name, nim, jurusan;


    private User(){
    }

    public User(String name, String nim, String jurusan){
        this.name = name;
        this.nim = nim;
        this.jurusan = jurusan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNim(){
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getJurusan(){
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }
}
