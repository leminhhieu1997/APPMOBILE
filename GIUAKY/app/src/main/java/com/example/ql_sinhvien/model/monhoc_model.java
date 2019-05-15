package com.example.ql_sinhvien.model;

public class monhoc_model {
    private String maMH;
    private String tenMH;
    private int soTiet;

    public monhoc_model() {
    }

    public monhoc_model(String maMH, String tenMH, int soTiet) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.soTiet = soTiet;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public int getSoTiet() {
        return soTiet;
    }

    public void setSoTiet(int soTiet) {
        this.soTiet = soTiet;
    }


}
