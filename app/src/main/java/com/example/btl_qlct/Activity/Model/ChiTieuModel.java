package com.example.btl_qlct.Activity.Model;

public class ChiTieuModel {

    String khoanchi, mota, ngay;
    int sotien;


    public ChiTieuModel(String khoanchi, String mota, String ngay, int sotien) {
        this.khoanchi = khoanchi;
        this.mota = mota;
        this.ngay = ngay;
        this.sotien = sotien;
    }

    public String getKhoanchi() {
        return khoanchi;
    }

    public void setKhoanchi(String khoanchi) {
        this.khoanchi = khoanchi;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getSotien() {
        return sotien;
    }

    public void setSotien(int sotien) {
        this.sotien = sotien;
    }
}
