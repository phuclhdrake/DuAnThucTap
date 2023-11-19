package com.example.asm.model;

import java.io.Serializable;

public class Sanpham {
    public int Id;
    public String TenSP;
    public int GiaSP;
    public String HinhAnhSP;
    public String MoTaSP;
    public int IdSP;

    public Sanpham(int id, String tenSP, int giaSP, String hinhAnhSP, String moTaSP, int idSP) {
        Id = id;
        TenSP = tenSP;
        GiaSP = giaSP;
        HinhAnhSP = hinhAnhSP;
        MoTaSP = moTaSP;
        IdSP = idSP;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public int getGiaSP() {
        return GiaSP;
    }

    public void setGiaSP(int giaSP) {
        GiaSP = giaSP;
    }

    public String getHinhAnhSP() {
        return HinhAnhSP;
    }

    public void setHinhAnhSP(String hinhAnhSP) {
        HinhAnhSP = hinhAnhSP;
    }

    public String getMoTaSP() {
        return MoTaSP;
    }

    public void setMoTaSP(String moTaSP) {
        MoTaSP = moTaSP;
    }

    public int getIdSP() {
        return IdSP;
    }

    public void setIdSP(int idSP) {
        IdSP = idSP;
    }
}
