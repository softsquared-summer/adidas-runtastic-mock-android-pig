package com.softsquared.runtastic.src.main.fragment.Profile.adapter;

public class ProfileItem {
    int icon;
    String title;
    String explain;

    public ProfileItem(int icon, String title, String explain) {
        this.icon = icon;
        this.title = title;
        this.explain = explain;
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

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
