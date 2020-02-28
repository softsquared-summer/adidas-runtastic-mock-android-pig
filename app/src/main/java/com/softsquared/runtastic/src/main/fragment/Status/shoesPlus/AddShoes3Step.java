package com.softsquared.runtastic.src.main.fragment.Status.shoesPlus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.main.fragment.Status.interfaces.AddShoes3StepView;
import com.softsquared.runtastic.src.main.fragment.Status.models.Sneakers;
import com.softsquared.runtastic.src.main.fragment.Status.models.SneakersRequest;
import com.softsquared.runtastic.src.main.fragment.Status.services.AddShoes3StepService;

public class AddShoes3Step extends BaseActivity implements AddShoes3StepView {
    Sneakers mSneakers;

    SeekBar mSeekBar;
    TextView mTvDistance, mTvUnit;
    int mDistance = 500;

    String mModelName,mBrandName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shoes3_step);

        Intent sneakersInfo = getIntent();
        mModelName = sneakersInfo.getStringExtra("modelName");
        mBrandName = sneakersInfo.getStringExtra("brandName");
        mSneakers = (Sneakers) sneakersInfo.getSerializableExtra("sneakers");

        Log.e("getModelNo", "" + mSneakers.getModelNo()); // o
        Log.e("getSizeType", "" + mSneakers.getSizeType()); // o
        Log.e("getSizeValue", "" + mSneakers.getSizeValue()); // x
        Log.e("getNickname", "" + mSneakers.getNickname()); // x
        Log.e("getColorNo", "" + mSneakers.getColorNo()); // o
        Log.e("getStartedAt", "" + mSneakers.getStartedAt()); // x
        Log.e("getLimitDistance", "" + mSneakers.getLimitDistance()); // o
        Log.e("getInitDistance", "" + mSneakers.getInitDistance()); // o

        mSeekBar = findViewById(R.id.add_third_seek_bar);
        mTvDistance = findViewById(R.id.add_third_tv_distance);
        mTvUnit = findViewById(R.id.add_third_tv_unit);
        setSeekBar();
    }

    public void customOnClickInThirdStep(View v) {
        switch (v.getId()) {
            case R.id.add_third_step_back:
                finish();
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
                break;
            case R.id.add_third_step_next:
                mSneakers.setLimitDistance(mDistance);
                tryPostSneakers(mSneakers);
                break;
            default:
                break;
        }
    }

    public void setSeekBar() {
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mDistance = seekBar.getProgress();
                updateSeekBar();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mDistance = seekBar.getProgress();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mDistance = seekBar.getProgress();
            }
        });
    }

    public void updateSeekBar() {
        mTvDistance.setText(new StringBuilder().append(mDistance));
        if (mDistance < 885) {
            mTvDistance.setTextColor(getColor(R.color.colorContinueGreen));
            mTvUnit.setTextColor(getColor(R.color.colorContinueGreen));
        } else if (mDistance < 1130) {
            mTvDistance.setTextColor(getColor(R.color.colorMedium));
            mTvUnit.setTextColor(getColor(R.color.colorMedium));
        } else {
            mTvDistance.setTextColor(getColor(R.color.colorDanger));
            mTvUnit.setTextColor(getColor(R.color.colorDanger));

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    public void tryPostSneakers(Sneakers sneakers) {
        showProgressDialog();

        Log.e("getModelNo", "" + sneakers.getModelNo()); // o
        Log.e("getSizeType", "" + sneakers.getSizeType()); // o
        Log.e("getSizeValue", "" + sneakers.getSizeValue()); // x
        Log.e("getNickname", "" + sneakers.getNickname()); // x
        Log.e("getColorNo", "" + sneakers.getColorNo()); // o
        Log.e("getStartedAt", "" + sneakers.getStartedAt()); // x
        Log.e("getLimitDistance", "" + sneakers.getLimitDistance()); // o
        Log.e("getInitDistance", "" + sneakers.getInitDistance()); // o

        final AddShoes3StepService service = new AddShoes3StepService(this);
        SneakersRequest request = new SneakersRequest(sneakers.getModelNo(),
                sneakers.getSizeType(), sneakers.getSizeValue(),
                sneakers.getNickname(), sneakers.getColorNo(),
                sneakers.getStartedAt(), sneakers.getLimitDistance(),
                sneakers.getInitDistance());

        service.postSneakers(request);
    }

    @Override
    public void validateSuccess(String message) {
        Intent intent = new Intent(getApplicationContext(),AddShoesCompleteActivity.class);
        intent.putExtra("brandName", mBrandName);
        intent.putExtra("modelName", mModelName);
        startActivity(intent);
        hideProgressDialog();
    }

    @Override
    public void validateFailure(String text) {
        hideProgressDialog();
    }
}
