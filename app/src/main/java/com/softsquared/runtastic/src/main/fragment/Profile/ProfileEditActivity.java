package com.softsquared.runtastic.src.main.fragment.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;

public class ProfileEditActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
    }

    public void customOnClick(View v) {
        switch (v.getId()) {
            case R.id.profile_edit_back:
                finish();
                break;
            default:
                break;
        }
    }
}
