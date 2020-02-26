package com.softsquared.runtastic.src.main.fragment.NewsPeed;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.softsquared.runtastic.R;

public class FragmentNewsPeed extends Fragment {
    ImageButton mBtnAddFriends, mBtnAlarm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_peed, container, false);
        mBtnAddFriends = rootView.findViewById(R.id.news_peed_add_friends);
        mBtnAlarm = rootView.findViewById(R.id.news_peed_alarm);



        setButtonTools();
        return rootView;
    }

    private void setButtonTools() {
        mBtnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mBtnAddFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),AddFriendsActivity.class);
                startActivity(intent);
            }
        });
    }

}
