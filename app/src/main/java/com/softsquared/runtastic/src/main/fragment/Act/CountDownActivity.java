package com.softsquared.runtastic.src.main.fragment.Act;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;

public class CountDownActivity extends BaseActivity {
    StartActTimer mActTimer;
    TextView mTvTimer;
    long mSaveCountDown;
    Animation mScaleUp,mScaleDown;

    boolean mStartAct;
    private int RESULT_OK = 1;
    private int ACT_MODE_START = 1; // 1일때 시작, 2일때 취소
    private int ACT_MODE_CANCEL = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        mStartAct = getIntent().getBooleanExtra("StartFlag",true);

        mScaleDown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale_down_animation);
        mScaleUp = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale_up_animation);

        mTvTimer = findViewById(R.id.count_down_timer);
        mActTimer = new StartActTimer(15000,1000);
        mActTimer.start();
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.count_down_btn_add_time:
                mActTimer.cancel();
                mActTimer = new StartActTimer(mSaveCountDown+10000,1000);
                mActTimer.start();
                break;
            case R.id.count_down_ll:
                Intent intent = new Intent();
                intent.putExtra("mode_flag", ACT_MODE_START);
                setResult(RESULT_OK, intent);
                finish();
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("mode_flag", ACT_MODE_CANCEL);
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.fade_out_animation, R.anim.fade_out_animation);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    class StartActTimer extends CountDownTimer {
        // 첫번째 인자 타이머 동작 총시간, 두번째 인자 카운트다운 되는 시간
        public StartActTimer(long millisInFuture, long countDownInterval)
        {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long time = millisUntilFinished/1000;
            mTvTimer.setText(Long.toString(time));
            mTvTimer.startAnimation(mScaleDown);
            mTvTimer.startAnimation(mScaleUp);
            mSaveCountDown = millisUntilFinished;
        }

        @Override
        public void onFinish() {
            Intent intent = new Intent();
            intent.putExtra("mode_flag", ACT_MODE_START);
            setResult(RESULT_OK, intent);
            finish();
        }

    }
}
