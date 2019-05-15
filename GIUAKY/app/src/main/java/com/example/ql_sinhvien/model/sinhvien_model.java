package com.example.ql_sinhvien.model;

import java.util.Date;

public class sinhvien_model {
    private String maSSV;
    private String hoVaTen;
    private int phai;
    private String ngaySinh;
    private String diaChi;
    private String sdt;
    private String maKhoa;


    public sinhvien_model(String maSSV, String hoVaTen, int phai, String ngaySinh, String diaChi, String sdt, String maKhoa) {
        this.maSSV = maSSV;
        this.hoVaTen = hoVaTen;
        this.phai = phai;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.maKhoa = maKhoa;
    }

    public sinhvien_model() {
    }


    public String getMaSSV() {
        return maSSV;
    }

    public void setMaSSV(String maSSV) {
        this.maSSV = maSSV;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public int getPhai() {
        return phai;
    }

    public void setPhai(int phai) {
        this.phai = phai;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }
}
