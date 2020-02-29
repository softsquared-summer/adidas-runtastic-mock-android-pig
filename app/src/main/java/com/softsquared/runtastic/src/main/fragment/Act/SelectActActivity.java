package com.softsquared.runtastic.src.main.fragment.Act;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;

public class SelectActActivity extends BaseActivity {

    ImageButton mBtnBack;
    int RESULT_ACT = 5;
    int type = 1;
    Intent resultAct = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_act);


        mBtnBack = findViewById(R.id.select_act_btn_back);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void selectActButton(View v) {
        switch (v.getId()) {
            case R.id.select_act_btn_running:
                type = 1;
                resultAct.putExtra("selectAct",type);
                setResult(RESULT_ACT, resultAct);
                finish();
                break;
            case R.id.select_act_btn_walking:
                type = 2;
                resultAct.putExtra("selectAct",type);
                setResult(RESULT_ACT, resultAct);
                finish();
                break;
            case R.id.select_act_btn_hiking:
                type = 3;
                resultAct.putExtra("selectAct",type);
                setResult(RESULT_ACT, resultAct);
                finish();
                break;
            case R.id.select_act_btn_cycling:
                type = 4;
                resultAct.putExtra("selectAct",type);
                setResult(RESULT_ACT, resultAct);
                finish();
                break;
            default:
                break;
        }
    }
}
