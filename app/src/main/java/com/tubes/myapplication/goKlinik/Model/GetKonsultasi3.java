package com.tubes.myapplication.goKlinik.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetKonsultasi3 {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<Konsultasi3> listKonsultasi;

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

    public List<Konsultasi3>  getmNotes() {
        return listKonsultasi;
    }
    public void setmNotes(List<Konsultasi3> mNotes) {
        this.listKonsultasi = mNotes;
    }
}
