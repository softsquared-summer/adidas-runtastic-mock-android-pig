package com.softsquared.runtastic.src.main.fragment.Status.shoesPlus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.main.fragment.Status.models.Sneakers;

public class AddShoes2Step extends BaseActivity {
    Sneakers mSneakers;

    EditText mEtDistance, mEtDate;

    String mDate = "20200229";
    int mInitDistance = 0;

    String mModelName,mBrandName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shoes2_step);
        mEtDistance = findViewById(R.id.add_second_et_init_distance);
        mEtDate = findViewById(R.id.add_second_et_date);

        // 운동화 정보 받는부분
        Intent sneakersInfo = getIntent();
        mModelName = sneakersInfo.getStringExtra("modelName");
        mBrandName = sneakersInfo.getStringExtra("brandName");
        mSneakers = (Sneakers) sneakersInfo.getSerializableExtra("sneakers");
    }

    public void customOnClickInSecondStep(View v) {
        switch (v.getId()) {
            case R.id.add_second_step_back:
                finish();
                overridePendingTransition(R.anim.right_in,R.anim.right_out);
                break;
            case R.id.add_second_step_next:
                if(mEtDate.getText().toString().length() != 0) {
                    mDate = mEtDate.getText().toString();
                }
                if(mEtDistance.getText().toString().length() != 0) {
                    mInitDistance = Integer.parseInt(mEtDistance.getText().toString());
                }

                mSneakers.setStartedAt(mDate);
                mSneakers.setInitDistance(mInitDistance);

                Intent intent = new Intent(getApplicationContext(),AddShoes3Step.class);
                intent.putExtra("brandName", mBrandName);
                intent.putExtra("modelName", mModelName);
                intent.putExtra("sneakers",mSneakers);
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
