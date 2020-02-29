package com.softsquared.runtastic.src.main.fragment.Profile.settingTab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;

public class TermsOfServiceActivity extends BaseActivity {

    ImageButton mBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_of_service);
        mBtnBack = findViewById(R.id.setting_tos_back);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
