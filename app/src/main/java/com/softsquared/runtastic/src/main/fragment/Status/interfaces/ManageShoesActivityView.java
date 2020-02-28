package com.softsquared.runtastic.src.main.fragment.Status.interfaces;

import com.softsquared.runtastic.src.main.fragment.Status.adapter.SneakersListItem;

import java.util.ArrayList;

public interface ManageShoesActivityView {
    void validateSuccess(String message);
    void validateFailure(String text);
    void getArrayShoes(ArrayList<SneakersListItem> result);
}
