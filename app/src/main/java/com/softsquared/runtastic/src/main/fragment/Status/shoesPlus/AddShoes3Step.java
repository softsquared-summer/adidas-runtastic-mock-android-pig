package com.softsquared.runtastic.src.main.fragment.Status.shoesPlus;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;

public class AddShoes3Step extends BaseActivity {

    SeekBar mSeekBar;
    TextView mTvDistance, mTvUnit;
    int mDistance = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shoes3_step);

        mSeekBar = findViewById(R.id.add_third_seek_bar);
        mTvDistance = findViewById(R.id.add_third_tv_distance);
        mTvUnit = findViewById(R.id.add_third_tv_unit);

        setSeekBar();

    }

    public void customOnClickInThirdStep(View v) {
        switch (v.getId()) {
            case R.id.add_third_step_back:
                finish();
                overridePendingTransition(R.anim.right_in,R.anim.right_out);
                break;
            case R.id.add_third_step_next:
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
        if(mDistance < 885) {
            mTvDistance.setTextColor(getColor(R.color.colorContinueGreen));
            mTvUnit.setTextColor(getColor(R.color.colorContinueGreen));
        } else if(mDistance < 1130) {
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
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
    }
}
