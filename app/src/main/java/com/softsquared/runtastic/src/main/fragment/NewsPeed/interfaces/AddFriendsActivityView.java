package com.softsquared.runtastic.src.main.fragment.NewsPeed.interfaces;

import com.softsquared.runtastic.src.main.fragment.NewsPeed.Adapter.FriendsListItem;

import java.util.ArrayList;

public interface AddFriendsActivityView {
    void validateSuccess(String message);
    void validateFailure(String text);
    void pasteFriendsList(ArrayList<FriendsListItem> result);
}
