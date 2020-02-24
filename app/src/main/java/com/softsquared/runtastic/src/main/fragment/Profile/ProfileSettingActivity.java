package com.softsquared.runtastic.src.main.fragment.Profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.main.fragment.Profile.adapter.ProfileSettingItem;
import com.softsquared.runtastic.src.main.fragment.Profile.adapter.ProfileSettingListAdapter;
import com.softsquared.runtastic.src.start.StartActivity;

import java.util.ArrayList;

import static com.softsquared.runtastic.src.ApplicationClass.X_ACCESS_TOKEN;

public class ProfileSettingActivity extends BaseActivity {
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;

    ListView mLvPrivacyList, mLvAppSettingList, mLvAddList;
    ArrayList<ProfileSettingItem> mPrivacyList = new ArrayList<>();
    ArrayList<ProfileSettingItem> mAppSettingList = new ArrayList<>();
    ArrayList<ProfileSettingItem> mAddList = new ArrayList<>();
    ProfileSettingListAdapter mPrivacyAdapter, mAppSettingAdapter, mAddAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);
        mSharedPreferences = getSharedPreferences(X_ACCESS_TOKEN, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        initArrayList();

        mLvAppSettingList = findViewById(R.id.profile_setting_lv_app);
        mLvPrivacyList = findViewById(R.id.profile_setting_lv_privacy);
        mLvAddList = findViewById(R.id.profile_setting_lv_add);

        mPrivacyAdapter = new ProfileSettingListAdapter(mPrivacyList,getApplicationContext(),R.layout.profile_setting_list_item);
        mAppSettingAdapter = new ProfileSettingListAdapter(mAppSettingList,getApplicationContext(),R.layout.profile_setting_list_item);
        mAddAdapter = new ProfileSettingListAdapter(mAddList,getApplicationContext(),R.layout.profile_setting_list_item);

        mLvPrivacyList.setAdapter(mPrivacyAdapter);
        mLvAppSettingList.setAdapter(mAppSettingAdapter);
        mLvAddList.setAdapter(mAddAdapter);

        setButtonTools();
    }

    public void customOnclick(View v) {
        switch (v.getId()) {
            case R.id.profile_setting_btn_logout:
                mEditor.clear();
                mEditor.commit();
                Intent intent = new Intent(this, StartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.profile_setting_btn_complete:
                finish();
            default:
                break;
        }
    }

    public void setButtonTools() {
        mLvPrivacyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    Intent intent = new Intent(getApplicationContext(),ProfileEditActivity.class);
                    startActivity(intent);
                } else if(position == 1) {

                } else if(position == 2) {

                } else if(position == 3) {

                }

            }
        });

        mLvAppSettingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        mLvAddList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    public void initArrayList(){
        mPrivacyList.add(new ProfileSettingItem(R.drawable.profile_edit,getString(R.string.profile_setting_edit),R.drawable.profile_btn_in," "));
        mPrivacyList.add(new ProfileSettingItem(R.drawable.profile_unit_img,getString(R.string.profile_setting_unit),R.drawable.profile_btn_in,getString(R.string.profile_setting_unit_ex)));
        mPrivacyList.add(new ProfileSettingItem(R.drawable.profile_privacy,getString(R.string.profile_setting_privacy),R.drawable.profile_btn_in," "));
        mPrivacyList.add(new ProfileSettingItem(R.drawable.profile_alarm,getString(R.string.profile_setting_alarm),R.drawable.profile_btn_in," "));

        mAppSettingList.add(new ProfileSettingItem(R.drawable.profile_apple_watch,getString(R.string.profile_setting_apple_watch),R.drawable.profile_btn_in, " "));
        mAppSettingList.add(new ProfileSettingItem(R.drawable.profile_partner,getString(R.string.profile_setting_partner),R.drawable.profile_btn_in, " "));
        mAppSettingList.add(new ProfileSettingItem(R.drawable.profile_sns,getString(R.string.profile_setting_sns),R.drawable.profile_btn_in, " "));

        mAddList.add(new ProfileSettingItem(R.drawable.profile_terms_of_service,getString(R.string.profile_setting_tos),R.drawable.profile_btn_in, " "));
        mAddList.add(new ProfileSettingItem(R.drawable.profile_adidas_running,getString(R.string.profile_setting_adidas),R.drawable.profile_btn_in, " "));
    }





}
