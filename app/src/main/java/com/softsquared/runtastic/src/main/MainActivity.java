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
import com.softsquared.runtastic.src.main.fragment.Act.FragmentAct;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.FragmentNewsPeed;
import com.softsquared.runtastic.src.main.fragment.Plan.FragmentPlan;
import com.softsquared.runtastic.src.main.fragment.Profile.FragmentProfile;
import com.softsquared.runtastic.src.main.fragment.Status.FragmentStatus;
import com.softsquared.runtastic.src.main.interfaces.MainActivityView;

public class MainActivity extends BaseActivity implements MainActivityView {
    private TextView mTvHelloWorld;

    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private FragmentAct mFragmentAct = new FragmentAct();
    private FragmentNewsPeed mFragmentNewsPeed;
    private FragmentPlan mFragmentPlan;
    private FragmentProfile mFragmentProfile;
    private FragmentStatus mFragmentStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.main_frameLayout, mFragmentAct).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.main_bottom_nav_bar);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
        bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {
                case R.id.menu_news_peed:
                    if (mFragmentNewsPeed == null) {
                        mFragmentNewsPeed = new FragmentNewsPeed();
                        mFragmentManager.beginTransaction().add(R.id.main_frameLayout, mFragmentNewsPeed).commit();
                    }
                    if (mFragmentNewsPeed != null)
                        mFragmentManager.beginTransaction().show(mFragmentNewsPeed).commit();
                    if (mFragmentStatus != null)
                        mFragmentManager.beginTransaction().hide(mFragmentStatus).commit();
                    if (mFragmentAct != null)
                        mFragmentManager.beginTransaction().hide(mFragmentAct).commit();
                    if (mFragmentPlan != null)
                        mFragmentManager.beginTransaction().hide(mFragmentPlan).commit();
                    if (mFragmentProfile != null)
                        mFragmentManager.beginTransaction().hide(mFragmentProfile).commit();
                    break;
                case R.id.menu_status:
                    if (mFragmentStatus == null) {
                        mFragmentStatus = new FragmentStatus();
                        mFragmentManager.beginTransaction().add(R.id.main_frameLayout, mFragmentStatus).commit();
                    }
                    if (mFragmentNewsPeed != null)
                        mFragmentManager.beginTransaction().hide(mFragmentNewsPeed).commit();
                    if (mFragmentStatus != null)
                        mFragmentManager.beginTransaction().show(mFragmentStatus).commit();
                    if (mFragmentAct != null)
                        mFragmentManager.beginTransaction().hide(mFragmentAct).commit();
                    if (mFragmentPlan != null)
                        mFragmentManager.beginTransaction().hide(mFragmentPlan).commit();
                    if (mFragmentProfile != null)
                        mFragmentManager.beginTransaction().hide(mFragmentProfile).commit();
                    break;
                case R.id.menu_act:
                    if (mFragmentAct == null) {
                        mFragmentAct = new FragmentAct();
                        mFragmentManager.beginTransaction().add(R.id.main_frameLayout, mFragmentAct).commit();
                    }
                    if (mFragmentNewsPeed != null)
                        mFragmentManager.beginTransaction().hide(mFragmentNewsPeed).commit();
                    if (mFragmentStatus != null)
                        mFragmentManager.beginTransaction().hide(mFragmentStatus).commit();
                    if (mFragmentAct != null)
                        mFragmentManager.beginTransaction().show(mFragmentAct).commit();
                    if (mFragmentPlan != null)
                        mFragmentManager.beginTransaction().hide(mFragmentPlan).commit();
                    if (mFragmentProfile != null)
                        mFragmentManager.beginTransaction().hide(mFragmentProfile).commit();
                    break;
                case R.id.menu_plan:
                    if (mFragmentPlan == null) {
                        mFragmentPlan = new FragmentPlan();
                        mFragmentManager.beginTransaction().add(R.id.main_frameLayout, mFragmentPlan).commit();
                    }
                    if (mFragmentNewsPeed != null)
                        mFragmentManager.beginTransaction().hide(mFragmentNewsPeed).commit();
                    if (mFragmentStatus != null)
                        mFragmentManager.beginTransaction().hide(mFragmentStatus).commit();
                    if (mFragmentAct != null)
                        mFragmentManager.beginTransaction().hide(mFragmentAct).commit();
                    if (mFragmentPlan != null)
                        mFragmentManager.beginTransaction().show(mFragmentPlan).commit();
                    if (mFragmentProfile != null)
                        mFragmentManager.beginTransaction().hide(mFragmentProfile).commit();
                    break;
                case R.id.menu_profile:
                    if (mFragmentProfile == null) {
                        mFragmentProfile = new FragmentProfile();
                        mFragmentManager.beginTransaction().add(R.id.main_frameLayout, mFragmentProfile).commit();
                    }
                    if (mFragmentNewsPeed != null)
                        mFragmentManager.beginTransaction().hide(mFragmentNewsPeed).commit();
                    if (mFragmentStatus != null)
                        mFragmentManager.beginTransaction().hide(mFragmentStatus).commit();
                    if (mFragmentAct != null)
                        mFragmentManager.beginTransaction().hide(mFragmentAct).commit();
                    if (mFragmentPlan != null)
                        mFragmentManager.beginTransaction().hide(mFragmentPlan).commit();
                    if (mFragmentProfile != null)
                        mFragmentManager.beginTransaction().show(mFragmentProfile).commit();
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

    public void customViewPagerButtonClick(View v) {
        switch (v.getId()) {
            case R.id.view_pager_btn_complete:
                mFragmentAct.clickedComplete();
                break;
            case R.id.view_pager_btn_continue:
                mFragmentAct.clickedContinue();
                break;
            default:
                break;
        }
    }
}
