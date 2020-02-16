package com.softsquared.runtastic.src.main;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.main.fragment.FragmentAct;
import com.softsquared.runtastic.src.main.fragment.FragmentNewsPeed;
import com.softsquared.runtastic.src.main.fragment.FragmentPlan;
import com.softsquared.runtastic.src.main.fragment.FragmentProfile;
import com.softsquared.runtastic.src.main.fragment.FragmentStatus;
import com.softsquared.runtastic.src.main.interfaces.MainActivityView;

public class MainActivity extends BaseActivity implements MainActivityView {
    private TextView mTvHelloWorld;

    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private FragmentAct mFragmentAct = new FragmentAct();
    private FragmentNewsPeed mFragmentNewsPeed = new FragmentNewsPeed();
    private FragmentPlan mFragmentPlan = new FragmentPlan();
    private FragmentProfile mFragmentProfile = new FragmentProfile();
    private FragmentStatus mFragmentStatus = new FragmentStatus();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.main_frameLayout, mFragmentNewsPeed).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.main_bottom_nav_bar);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.menu_news_peed:
                    transaction.replace(R.id.main_frameLayout, mFragmentNewsPeed).commitAllowingStateLoss();
                    break;
                case R.id.menu_status:
                    transaction.replace(R.id.main_frameLayout, mFragmentStatus).commitAllowingStateLoss();
                    break;
                case R.id.menu_act:
                    transaction.replace(R.id.main_frameLayout, mFragmentAct).commitAllowingStateLoss();
                    break;
                case R.id.menu_plan:
                    transaction.replace(R.id.main_frameLayout, mFragmentPlan).commitAllowingStateLoss();
                    break;
                case R.id.menu_profile:
                    transaction.replace(R.id.main_frameLayout, mFragmentProfile).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }

    private void tryGetTest() {
        showProgressDialog();

        final MainService mainService = new MainService(this);
        mainService.getTest();
    }

    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
        mTvHelloWorld.setText(text);
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
           // case :
              //  break;
            default:
                break;
        }
    }
}
