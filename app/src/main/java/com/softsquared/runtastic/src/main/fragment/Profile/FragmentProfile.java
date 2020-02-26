package com.softsquared.runtastic.src.main.fragment.Profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.main.adapter.ProfileItem;
import com.softsquared.runtastic.src.main.adapter.ProfileItemAdapter;
import com.softsquared.runtastic.src.main.fragment.Profile.interfaces.FragmentProfileView;

import java.util.ArrayList;

public class FragmentProfile extends Fragment implements FragmentProfileView {

    public ProgressDialog mProgressDialog;
    private ProfileItemAdapter mAdapter;
    ListView mListView;
    ArrayList<ProfileItem> mItemArrayList = new ArrayList<>();
    ImageButton mBtnSetting;
    TextView mTvName, mTvCreatedAt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile , container, false);
        mListView = rootView.findViewById(R.id.profile_lv_list);
        mBtnSetting = rootView.findViewById(R.id.profile_btn_setting);
        mTvName = rootView.findViewById(R.id.fragment_profile_tv_name);
        mTvCreatedAt = rootView.findViewById(R.id.fragment_profile_tv_created);


        final FragmentProfileService fragmentProfileService = new FragmentProfileService(this);
        //fragmentProfileService.getProfile();
        //showProgressDialog();

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

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
            }
        });
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
    public void getProfileResult(String name, String createdAt, int friendCnt) {
        mTvName.setText(name);
        mTvCreatedAt.setText(getString(R.string.profile_sign_up_date) + createdAt);
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
