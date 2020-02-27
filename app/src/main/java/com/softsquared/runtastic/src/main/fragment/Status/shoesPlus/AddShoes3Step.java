package com.softsquared.runtastic.src.main.fragment.Status.shoesPlus;

import android.os.Bundle;
import android.view.View;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;

public class AddShoes3Step extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shoes3_step);
    }

    public void customOnClickInThirdStep(View v) {
        switch (v.getId()) {
            case R.id.add_second_step_back:
                finish();
                break;
            case R.id.add_second_step_next:
                break;
            default:
                break;
        }
    }
}
