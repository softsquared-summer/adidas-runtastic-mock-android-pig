package com.softsquared.runtastic.src.main.fragment.Status.adapter;

import com.google.gson.annotations.SerializedName;

public class BrandListItem {
    @SerializedName("brandNo")
    String brandNo;

    @SerializedName("brandName")
    String brandName;

    public BrandListItem(String brandNo) {
        this.brandNo = brandNo;
    }

    public String getBrandNo() {
        return brandNo;
    }

    public void setBrandNo(String brandNo) {
        this.brandNo = brandNo;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
