package com.softsquared.runtastic.src.main.fragment.Status.shoesPlus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;

public class AddShoes2Step extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shoes2_step);
    }

    public void customOnClickInSecondStep(View v) {
        switch (v.getId()) {
            case R.id.add_second_step_back:
                finish();
                overridePendingTransition(R.anim.right_in,R.anim.right_out);
                break;
            case R.id.add_second_step_next:
                Intent intent = new Intent(getApplicationContext(),AddShoes3Step.class);
                startActivity(intent);
                overridePendingTransition(R.anim.left_in,R.anim.left_out);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
    }
}
