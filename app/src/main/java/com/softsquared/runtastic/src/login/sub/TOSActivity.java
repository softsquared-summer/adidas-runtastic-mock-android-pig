package com.softsquared.runtastic.src.login.sub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;

public class TOSActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tos);
        mActList.add(this);
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
        startActivity(intent);
    }

}
