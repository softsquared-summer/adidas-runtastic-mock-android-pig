package com.softsquared.runtastic.src.main.fragment.NewsPeed.interfaces;

import com.softsquared.runtastic.src.main.fragment.NewsPeed.models.FindFriendResponse;

public interface FindFriendActivityView {
    void getFriendsInfo(FindFriendResponse.FriendResult result);

    void sendNoInfo();

    void validateFailure(String message);

    void validateSuccess(String text, int code);
}
