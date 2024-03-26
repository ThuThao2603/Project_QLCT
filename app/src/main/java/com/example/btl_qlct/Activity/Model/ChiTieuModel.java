package com.example.btl_qlct.Activity.Model;

import java.util.HashMap;
import java.util.Map;

public class ChiTieuModel {

    String khoanchi, mota, ngay, id_nguoidung, sotien, id_chitieu;



    public ChiTieuModel(){

    }
    public ChiTieuModel(String khoanchi, String mota, String ngay, String id_nguoidung, String sotien, String id_chitieu) {
        this.khoanchi = khoanchi;
        this.mota = mota;
        this.ngay = ngay;
        this.id_nguoidung = id_nguoidung;
        this.sotien = sotien;
        this.id_chitieu = id_chitieu;
    }

    public String getId_chitieu() {
        return id_chitieu;
    }

    public void setId_chitieu(String id_chitieu) {
        this.id_chitieu = id_chitieu;
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


    //cập nhật nhiều thuộc tính của chi tiêu
    public Map<String, Object> toMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("khoanchi", khoanchi);
        map.put("mota", mota);
        map.put("ngay", ngay);
        map.put("sotien", sotien);
        //map.put("id_nguoidung", id_nguoidung);


        return map;
    }
}
