package com.softsquared.runtastic.src.main.fragment.Profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.main.fragment.Profile.adapter.ProfileItem;
import com.softsquared.runtastic.src.main.fragment.Profile.adapter.ProfileItemAdapter;
import com.softsquared.runtastic.src.main.fragment.Profile.interfaces.FragmentProfileView;
import com.softsquared.runtastic.src.main.fragment.Profile.models.ProfileResponse;

import java.util.ArrayList;

public class FragmentProfile extends Fragment implements FragmentProfileView {

    public ProgressDialog mProgressDialog;
    private ProfileItemAdapter mAdapter;
    ListView mListView;
    ArrayList<ProfileItem> mItemArrayList = new ArrayList<>();
    ImageButton mBtnSetting;
    TextView mTvName, mTvCreatedAt;
    ImageView mIvProfileImg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile , container, false);
        showProgressDialog();
        mListView = rootView.findViewById(R.id.profile_lv_list);
        mBtnSetting = rootView.findViewById(R.id.profile_btn_setting);
        mTvName = rootView.findViewById(R.id.fragment_profile_tv_name);
        mTvCreatedAt = rootView.findViewById(R.id.fragment_profile_tv_created);
        mIvProfileImg = rootView.findViewById(R.id.fragment_profile_iv_img);
        mIvProfileImg.setBackground(new ShapeDrawable(new OvalShape()));
        mIvProfileImg.setClipToOutline(true);


        final FragmentProfileService fragmentProfileService = new FragmentProfileService(this);
        fragmentProfileService.getProfile();

        setButtonTools();
        return rootView;
    }


    public void setButtonTools(){
        mBtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfileSettingActivity.class);
                startActivity(intent);
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("click",position + " ");
            }
        });
    }

    @Override
    public void validateSuccess(String text, int code) {
        Log.e("[Log.e] profile_tab","message : " + text);
        Log.e("[Log.e] profile_tab","code : " + code);
        hideProgressDialog();
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public void getProfileResult(ProfileResponse.ProfileResult result) {
        mTvName.setText(result.getFirstName() + " " + result.getLastName());
        mTvCreatedAt.setText(getString(R.string.profile_sign_up_date) + " " +result.getCreatedAt());
        Glide.with(getContext()).load(result.getProfileImage()).into(mIvProfileImg);

        String countFriends = "친구 " + result.getFriendCnt() + "명";

        mItemArrayList.add(new ProfileItem(R.drawable.profile_friends,countFriends,getString(R.string.profile_friend_ex)));
        mItemArrayList.add(new ProfileItem(R.drawable.profile_group,getString(R.string.profile_group),getString(R.string.profile_group_ex)));
        mItemArrayList.add(new ProfileItem(R.drawable.profile_leader_board,getString(R.string.profile_leader),getString(R.string.profile_leader_ex)));
        mItemArrayList.add(new ProfileItem(R.drawable.profile_premium,getString(R.string.profile_premium),getString(R.string.profile_premium_ex)));

        mAdapter = new ProfileItemAdapter(mItemArrayList,getContext(),R.layout.fragment_profile_list_item);
        mListView.setAdapter(mAdapter);

        hideProgressDialog();
    }


    public void showCustomToast(final String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
