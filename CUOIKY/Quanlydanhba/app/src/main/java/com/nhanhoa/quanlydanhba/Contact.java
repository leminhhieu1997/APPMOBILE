package com.nhanhoa.quanlydanhba;

public class Contact {
    private String name;
    private String phone;
    private int image;
    private int sttCall;

    public Contact() {
    }

    public Contact(String name, String phone, int image) {
        this.name = name;
        this.phone = phone;
        this.image = image;
    }

    public Contact(String name, String phone, int image, int sttCall) {
        this.name = name;
        this.phone = phone;
        this.image = image;
        this.sttCall = sttCall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getSttCall() {
        return sttCall;
    }

    public void setSttCall(int sttCall) {
        this.sttCall = sttCall;
    }
}
