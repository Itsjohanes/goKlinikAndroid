package com.tubes.myapplication.goKlinik.Model;

import com.google.gson.annotations.SerializedName;

public class Pesanan {
    @SerializedName("id_pengiriman")
    private String idPengiriman;
    @SerializedName("status")
    private String status;
    @SerializedName("detail")
    private String detail;
    @SerializedName("total")
    private String total;
    @SerializedName("id_pasien")
    private String idPasien;

    public Pesanan(){}

    public Pesanan(String idPengiriman, String status, String detail, String total, String idPasien) {
        this.idPengiriman = idPengiriman;
        this.status = status;
        this.detail = detail;
        this.total = total;
        this.idPasien = idPasien;
    }

    public String getIdPesanan() {
        return idPengiriman;
    }
    public void setIdPesanan(String idPesanan) {
        this.idPengiriman = idPesanan;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTotal() {
        return total;
    }
    public void setTotal(String total) {
        this.total = total;
    }

    public String getIdPasien() {
        return idPasien;
    }
    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }
}
