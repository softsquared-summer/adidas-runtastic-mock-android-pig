package com.softsquared.runtastic.src.main.fragment.Act;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

public class CountDownActivity extends BaseActivity {
    StartActTimer mActTimer;
    TextView mTvTimer;
    Timer timer = new Timer();
    TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);

        mTvTimer = findViewById(R.id.count_down_timer);
        mActTimer = new StartActTimer(15000,1000);
        mActTimer.start();
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.count_down_btn_add_time:
                break;
            default:
                break;
        }
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
        }

        @Override
        public void onFinish() {
            finish();
        }
    }
}