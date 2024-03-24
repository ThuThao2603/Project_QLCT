package com.example.btl_qlct.Activity.Model;

public class ChiTieuModel {

    private String KhoanChi, MoTa, Ngay;
    private int SoTien;

    public ChiTieuModel() {
    }

    public ChiTieuModel(String khoanChi, String moTa, String ngay, int soTien) {
        KhoanChi = khoanChi;
        MoTa = moTa;
        Ngay = ngay;
        SoTien = soTien;
    }

    public String getKhoanChi() {
        return KhoanChi;
    }

    public void setKhoanChi(String khoanChi) {
        KhoanChi = khoanChi;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String ngay) {
        Ngay = ngay;
    }

    public int getSoTien() {
        return SoTien;
    }

    public void setSoTien(int soTien) {
        SoTien = soTien;
    }

    @Override
    public String toString() {
        return "ChiTieuModel{" +
                "KhoanChi='" + KhoanChi + '\'' +
                ", MoTa='" + MoTa + '\'' +
                ", Ngay='" + Ngay + '\'' +
                ", SoTien=" + SoTien +
                '}';
    }
}
