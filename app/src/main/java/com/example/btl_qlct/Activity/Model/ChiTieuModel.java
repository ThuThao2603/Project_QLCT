package com.example.btl_qlct.Activity.Model;

public class ChiTieuModel {

    String khoanchi, mota, ngay, id_nguoidung, sotien;

    public ChiTieuModel(){ }
    public ChiTieuModel(String khoanchi, String mota, String ngay, String id_nguoidung, String sotien) {
        this.khoanchi = khoanchi;
        this.mota = mota;
        this.ngay = ngay;
        this.id_nguoidung = id_nguoidung;
        this.sotien = sotien;
    }

    public String getId_nguoidung() {
        return id_nguoidung;
    }

    public void setId_nguoidung(String id_nguoidung) {
        this.id_nguoidung = id_nguoidung;
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

    public String getSotien() {
        return sotien;
    }

    public void setSotien(String sotien) {
        this.sotien = sotien;
    }

    @Override
    public String toString() {
        return "ChiTieuModel{" +
                "khoanchi='" + khoanchi + '\'' +
                ", mota='" + mota + '\'' +
                ", ngay='" + ngay + '\'' +
                ", id_nguoidung='" + id_nguoidung + '\'' +
                ", sotien='" + sotien + '\'' +
                '}';
    }
}
