package com.tubes.myapplication.goKlinik.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetKonsultasi2 {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<Konsultasi2> listKonsultasi;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public List<Konsultasi2>  getmNotes() {
        return listKonsultasi;
    }
    public void setmNotes(List<Konsultasi2> mNotes) {
        this.listKonsultasi = mNotes;
    }
}
