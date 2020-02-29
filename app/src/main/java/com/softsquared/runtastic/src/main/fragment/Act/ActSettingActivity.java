package com.softsquared.runtastic.src.main.fragment.Act;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;

public class ActSettingActivity extends BaseActivity {

    Button mBtnComplete;
    ConstraintLayout mBtnSelectAct;

    ImageView mIvIcon;
    TextView mTvtitle;
    int type = 1;

    int REQUEST_ACT = 10;

    int RESULT_ACT = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_setting);
        mBtnComplete = findViewById(R.id.act_setting_complete);
        mBtnSelectAct = findViewById(R.id.act_setting_btn_set_Act);

        mIvIcon = findViewById(R.id.act_setting_icon);
        mTvtitle = findViewById(R.id.act_setting_name);

        mBtnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultAct = new Intent();
                resultAct.putExtra("setAct",type);
                setResult(RESULT_OK,resultAct);
                finish();
            }
        });
        mBtnSelectAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectActActivity.class);
                startActivityForResult(intent,REQUEST_ACT);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_ACT) {
            return;
        }
        if(requestCode == REQUEST_ACT) {
            switch (data.getIntExtra("selectAct",0)) {
                case 1:
                    mIvIcon.setImageResource(R.drawable.rec_running);
                    mTvtitle.setText("러닝");
                    type = 1;
                    break;
                case 2:
                    mIvIcon.setImageResource(R.drawable.rec_walking);
                    mTvtitle.setText("걷기");
                    type = 2;
                    break;
                case 3:
                    mIvIcon.setImageResource(R.drawable.rec_hiking);
                    mTvtitle.setText("하이킹");
                    type = 3;
                    break;
                case 4:
                    mIvIcon.setImageResource(R.drawable.rec_cycling);
                    mTvtitle.setText("사이클링");
                    type = 4;
                    break;
            }

        }
    }
}
