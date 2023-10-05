package com.lisau.myabsensi.model;

public class User {
    private String id;
    private String nim;
    private String nama;
    private String tanggal;
    private String jam;
    private String status;
    private String telat;

    public User(String id, String nim, String nama, String tanggal, String jam, String status, String telat){
        this.id = id;
        this.nim = nim;
        this.nama = nama;
        this.tanggal = tanggal;
        this.jam = jam;
        this.status = status;
        this.telat = telat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNim(){
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal(){
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    public String getJam() { return jam; }
    public void setJam(String jam) {this.jam = jam; }
    public String getStatus() { return status; }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getTelat() {
        return telat;
    }
    public void setTelat(String telat) {
        this.telat = telat;
    }

}

