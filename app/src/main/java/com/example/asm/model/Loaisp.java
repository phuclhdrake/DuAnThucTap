package com.example.asm.model;

public class Loaisp {
    public int Id;
    public String TenLoaiSP;
    public String HinhLoaiSP;

    public Loaisp(int id, String tenLoaiSP, String hinhLoaiSP) {
        Id = id;
        TenLoaiSP = tenLoaiSP;
        HinhLoaiSP = hinhLoaiSP;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenLoaiSP() {
        return TenLoaiSP;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        TenLoaiSP = tenLoaiSP;
    }

    public String getHinhLoaiSP() {
        return HinhLoaiSP;
    }

    public void setHinhLoaiSP(String hinhLoaiSP) {
        HinhLoaiSP = hinhLoaiSP;
    }
}
