package com.softsquared.runtastic.src.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.main.Profile.ProfileSettingActivity;
import com.softsquared.runtastic.src.main.adapter.ProfileItem;
import com.softsquared.runtastic.src.main.adapter.ProfileItemAdapter;

import java.util.ArrayList;

public class FragmentProfile extends Fragment {

    private ProfileItemAdapter mAdapter;
    ListView mListView;
    ArrayList<ProfileItem> mItemArrayList = new ArrayList<>();
    ImageButton mBtnSetting;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile , container, false);
        mListView = rootView.findViewById(R.id.profile_lv_list);
        mBtnSetting = rootView.findViewById(R.id.profile_btn_setting);


        setButtonTools();
        setmListView();
        return rootView;
    }

    private void setmListView(){

        mItemArrayList.add(new ProfileItem(R.drawable.profile_friends,getString(R.string.profile_friend),getString(R.string.profile_friend_ex)));
        mItemArrayList.add(new ProfileItem(R.drawable.profile_group,getString(R.string.profile_group),getString(R.string.profile_group_ex)));
        mItemArrayList.add(new ProfileItem(R.drawable.profile_leader_board,getString(R.string.profile_leader),getString(R.string.profile_leader_ex)));
        mItemArrayList.add(new ProfileItem(R.drawable.profile_premium,getString(R.string.profile_premium),getString(R.string.profile_premium_ex)));

        mAdapter = new ProfileItemAdapter(mItemArrayList,getContext(),R.layout.fragment_profile_list_item);
        mListView.setAdapter(mAdapter);
    }

    public void setButtonTools(){
        mBtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfileSettingActivity.class);
                startActivity(intent);
            }
        });
    }
}
