package com.softsquared.runtastic.src.main.fragment.Status.interfaces;

import com.softsquared.runtastic.src.main.fragment.Status.adapter.BrandListItem;
import com.softsquared.runtastic.src.main.fragment.Status.adapter.ShoesItem;

import java.util.ArrayList;

public interface SearchShoesActivityView {
    void getArrayBrand(ArrayList<BrandListItem> result);

    void getArrayModel(ArrayList<ShoesItem> result);

    void hideLoading();
}
