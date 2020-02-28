package com.softsquared.runtastic.src.main.fragment.Act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;

public class ActSettingActivity extends BaseActivity {

    Button mBtnComplete;
    ConstraintLayout mBtnSelectAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_setting);
        mBtnComplete = findViewById(R.id.act_setting_complete);
        mBtnSelectAct = findViewById(R.id.act_setting_btn_set_Act);

        mBtnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBtnSelectAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectActActivity.class);
                startActivityForResult(intent,1);
            }
        });

    }
}
