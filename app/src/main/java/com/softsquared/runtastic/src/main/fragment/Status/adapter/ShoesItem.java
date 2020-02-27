package com.softsquared.runtastic.src.main.fragment.Status.adapter;

public class ShoesItem {
    int iconImg;
    String title;
    String sub;
    int subImg;

    public ShoesItem(int iconImg, String title, String sub, int subImg) {
        this.iconImg = iconImg;
        this.title = title;
        this.sub = sub;
        this.subImg = subImg;
    }

    public int getIconImg() {
        return iconImg;
    }

    public void setIconImg(int iconImg) {
        this.iconImg = iconImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public int getSubImg() {
        return subImg;
    }

    public void setSubImg(int subImg) {
        this.subImg = subImg;
    }
}
