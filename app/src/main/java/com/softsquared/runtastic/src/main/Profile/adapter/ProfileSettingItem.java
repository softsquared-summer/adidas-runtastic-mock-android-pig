package com.softsquared.runtastic.src.main.Profile.adapter;

public class ProfileSettingItem {
    int icon;
    String title;
    int inIcon;
    String unit;

    public ProfileSettingItem(int icon, String title, int inIcon, String unit) {
        this.icon = icon;
        this.title = title;
        this.inIcon = inIcon;
        this.unit = unit;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getInIcon() {
        return inIcon;
    }

    public void setInIcon(int inIcon) {
        this.inIcon = inIcon;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
