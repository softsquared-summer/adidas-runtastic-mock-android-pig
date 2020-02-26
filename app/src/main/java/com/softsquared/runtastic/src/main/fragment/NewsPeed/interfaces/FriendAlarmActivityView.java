package com.softsquared.runtastic.src.main.fragment.NewsPeed.interfaces;

import com.softsquared.runtastic.src.main.fragment.NewsPeed.Adapter.ReceiveFriendItem;

import java.util.ArrayList;

public interface FriendAlarmActivityView {
    void validateSuccess(String message);
    void validateFailure(String text);
    void getArrayReceive(ArrayList<ReceiveFriendItem> result);
}
