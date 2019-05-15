package com.example.bt3listview.Model;

public class Soccer {
    private int avatar;
    private String name;
    private int country;

    public Soccer(int avatar, String name, int country) {
        this.avatar = avatar;
        this.name = name;
        this.country = country;
    }

    public int getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public int getCountry() {
        return country;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(int country) {
        this.country = country;
    }
}
