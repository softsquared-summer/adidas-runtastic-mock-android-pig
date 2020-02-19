package com.softsquared.runtastic.src.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.login.interfaces.SignUpActivityView;
import com.softsquared.runtastic.src.login.sub.TOSActivity;

public class SignUpActivity extends BaseActivity implements SignUpActivityView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up_btn_profile_img:
                break;
            case R.id.sign_up_btn_join_top:
                redirectTOSActivity();
                break;
            case R.id.sign_up_btn_join_bottom:
                redirectTOSActivity();
                break;
            case R.id.sign_up_btn_back:
                finish();
                break;
            default:
                break;
        }
    }

    private void redirectTOSActivity(){
        Intent intent = new Intent(getApplicationContext(), TOSActivity.class);
        startActivity(intent);
    }

    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }
}
