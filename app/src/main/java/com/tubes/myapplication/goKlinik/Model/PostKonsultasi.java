package com.tubes.myapplication.goKlinik.Model;

import com.google.gson.annotations.SerializedName;

public class PostKonsultasi {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    Konsultasi mNotes;

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

    public Konsultasi getmNotes() {
        return mNotes;
    }
    public void setmNotes(Konsultasi mNotes) {
        this.mNotes = mNotes;
    }
}
