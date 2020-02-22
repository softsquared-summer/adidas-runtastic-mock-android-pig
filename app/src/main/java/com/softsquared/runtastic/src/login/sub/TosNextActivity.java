package com.softsquared.runtastic.src.login.sub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.sign.SignUpNextActivity;

public class TosNextActivity extends BaseActivity {
    String mFname;
    int mUserNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tos_next);

        mFname = getIntent().getStringExtra("name");
        mUserNo = getIntent().getIntExtra("userNo",0);
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.tos_next_btn_admit:
                redirectSignUpNextActivty();
                break;

            default:
                break;
        }
    }

    private void redirectSignUpNextActivty(){
        Intent intent = new Intent(getApplicationContext(), SignUpNextActivity.class);
        intent.putExtra("name",mFname);
        intent.putExtra("userNo",mUserNo);
        startActivity(intent);
    }

}
