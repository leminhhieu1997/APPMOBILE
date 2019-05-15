package com.example.ql_sinhvien.model;

public class diem_model {

    private String maSSV;
    private String maMH;
    private int Diem;


    public diem_model() {
    }

    public diem_model(String maSSV, String maMH, int diem) {
        this.maSSV = maSSV;
        this.maMH = maMH;
        Diem = diem;
    }

    public String getMaSSV() {
        return maSSV;
    }

    public void setMaSSV(String maSSV) {
        this.maSSV = maSSV;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public int getDiem() {
        return Diem;
    }

    public void setDiem(int diem) {
        Diem = diem;
    }
}
