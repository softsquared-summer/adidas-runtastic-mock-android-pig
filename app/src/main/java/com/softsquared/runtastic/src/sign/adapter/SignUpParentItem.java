package com.softsquared.runtastic.src.sign.adapter;

import android.view.View;

public class SignUpParentItem {
    int icon;
    String title;
    View v;


    public SignUpParentItem(int icon, String title) {
        this.icon = icon;
        this.title = title;
        v= null;
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

    public View getV() {
        return v;
    }

    public void setV(View v) {
        this.v = v;
    }
}
