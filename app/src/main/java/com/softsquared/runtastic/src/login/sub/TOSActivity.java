package com.softsquared.runtastic.src.login.sub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;

public class TOSActivity extends BaseActivity {
    String mFname;
    int mUserNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tos);
        mActList.add(this);

        mFname = getIntent().getStringExtra("name");
        mUserNo = getIntent().getIntExtra("userNo",0);
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.tos_btn_admit:
                redirectTosNextActivity();
                break;
            case R.id.tos_btn_cancel:
                finish();
                break;
            default:
                break;
        }
    }

    private void redirectTosNextActivity(){
        Intent intent = new Intent(getApplicationContext(),TosNextActivity.class);
        intent.putExtra("name",mFname);
        intent.putExtra("userNo",mUserNo);
        startActivity(intent);
    }

}
