package com.softsquared.runtastic.src.main.fragment.Status.adapter;

public class RecentItem {
    private int mainIcon;
    private int subIcon;
    private String maintitle;
    private String subtitle;
    private boolean check;

    public RecentItem(int mainIcon, int subIcon, String maintitle, String subtitle) {
        this.mainIcon = mainIcon;
        this.subIcon = subIcon;
        this.maintitle = maintitle;
        this.subtitle = subtitle;
    }

    public int getMainIcon() {
        return mainIcon;
    }

    public void setMainIcon(int mainIcon) {
        this.mainIcon = mainIcon;
    }

    public int getSubIcon() {
        return subIcon;
    }

    public void setSubIcon(int subIcon) {
        this.subIcon = subIcon;
    }

    public String getMaintitle() {
        return maintitle;
    }

    public void setMaintitle(String maintitle) {
        this.maintitle = maintitle;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
