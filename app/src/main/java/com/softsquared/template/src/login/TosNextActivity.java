package com.softsquared.template.src.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.softsquared.template.R;
import com.softsquared.template.src.BaseActivity;

public class TosNextActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tos_next);
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
        Intent intent = new Intent(getApplicationContext(),SignUpNextActivity.class);
        startActivity(intent);
    }
}
