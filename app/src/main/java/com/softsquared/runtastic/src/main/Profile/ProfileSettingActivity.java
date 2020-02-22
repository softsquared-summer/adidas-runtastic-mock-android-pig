package com.softsquared.runtastic.src.main.Profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.start.StartActivity;

import static com.softsquared.runtastic.src.ApplicationClass.X_ACCESS_TOKEN;

public class ProfileSettingActivity extends BaseActivity {
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);
        mSharedPreferences = getSharedPreferences(X_ACCESS_TOKEN, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
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
            default:
                break;
        }
    }
}
