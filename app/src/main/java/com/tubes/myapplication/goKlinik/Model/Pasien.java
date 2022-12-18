package com.tubes.myapplication.goKlinik.Model;

import com.google.gson.annotations.SerializedName;

public class Pasien {
    @SerializedName("id_pasien")
    private String id_pasien;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("nama")
    private String nama;
    @SerializedName("tgl_lahir")
    private String tgl_lahir;
    @SerializedName("jenkel")
    private String jenkel;
    @SerializedName("no_hp")
    private String no_hp;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;

    public Pasien(){}

    public Pasien(String id_pasien, String username, String password, String nama,String tgl_lahir, String jenkel, String no_hp, String alamat) {
        this.id_pasien = id_pasien;
        this.username = username;
        this.password = password;
        this.nama=nama;
        this.tgl_lahir = tgl_lahir;
        this.jenkel = jenkel;
        this.no_hp = no_hp;
        this.alamat = alamat;
    }

    public String getId() {
        return id_pasien;
    }
    public void setId(String id) {
        this.id_pasien = id_pasien;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }
    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getJenkel() {
        return jenkel;
    }
    public void setJenkel(String jenkel) {
        this.jenkel = jenkel;
    }

    public String getNo_hp() {
        return no_hp;
    }
    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }


   public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage(){
        return message;
    }
    public void setMessage(){
        this.message = message;
    }

}
