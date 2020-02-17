package com.softsquared.runtastic.src.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.softsquared.runtastic.R;

public class FragmentAct extends Fragment implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;
    private GridLayout mGridExercise;
    private LinearLayout mLinearTop,mLinearBottm;
    private View mTopLine,mBottomLine;
    Animation mSlideUp, mSlideDown;
    BottomNavigationView mBottomNav;

    boolean mAnimationFlag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_act, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.act_map);
        mapFragment.getMapAsync(this);

        mGridExercise = rootView.findViewById(R.id.act_gl_time);
        mLinearTop = rootView.findViewById(R.id.act_ll_top_bar);
        mLinearBottm = rootView.findViewById(R.id.act_ll_bottom_bar);
        mTopLine = rootView.findViewById(R.id.act_v_top_line);
        mBottomLine = getActivity().findViewById(R.id.main_v_bottom_line);
        mBottomNav = getActivity().findViewById(R.id.main_bottom_nav_bar);

        mSlideDown = AnimationUtils.loadAnimation(getContext(),R.anim.slide_down);

        mSlideUp = AnimationUtils.loadAnimation(getContext(),
                R.anim.slide_up);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        LatLng SEOUL = new LatLng(37.56, 126.97);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        mGoogleMap.addMarker(markerOptions);

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                animation();
            }
        });
    }

    public void animation(){
        System.out.println("상단바: " + mLinearTop.getHeight() + " , 그리드레이아웃 : " + mGridExercise.getHeight() + " , 시작버튼 : " + mLinearBottm.getHeight() + ", 바텀nav : " + mBottomNav.getHeight());
        TranslateAnimation animateDownBottom = new TranslateAnimation(0, 0, 0, mLinearBottm.getHeight()+mBottomNav.getHeight()+45);
        TranslateAnimation animateUpBottom = new TranslateAnimation(0, 0, mLinearBottm.getHeight()+mBottomNav.getHeight()+45, 0);
        TranslateAnimation animateUpTop = new TranslateAnimation(0,0,0,-mGridExercise.getHeight() - mLinearTop.getHeight());
        TranslateAnimation animateDownTop = new TranslateAnimation(0,0,-mGridExercise.getHeight() - mLinearTop.getHeight(),0);
        animateUpBottom.setDuration(500);
        animateUpBottom.setFillAfter(true);
        animateDownBottom.setDuration(500);
        animateDownBottom.setFillAfter(true);
        animateUpTop.setDuration(500);
        animateUpTop.setFillAfter(true);
        animateDownTop.setDuration(500);
        animateDownTop.setFillAfter(true);

        if(!mAnimationFlag){
            mGridExercise.setVisibility(View.VISIBLE);
            mGridExercise.startAnimation(animateUpTop);

            mLinearBottm.setVisibility(View.VISIBLE);
            mBottomNav.setVisibility(View.VISIBLE);
            mLinearBottm.startAnimation(animateDownBottom);
            mBottomNav.startAnimation(animateDownBottom);

            mTopLine.setVisibility(View.INVISIBLE);
            mBottomLine.setVisibility(View.INVISIBLE);
        } else {
            mGridExercise.setVisibility(View.INVISIBLE);
            mGridExercise.startAnimation(animateDownTop);

            mLinearBottm.setVisibility(View.INVISIBLE);
            mBottomNav.setVisibility(View.INVISIBLE);
            mLinearBottm.startAnimation(animateUpBottom);
            mBottomNav.startAnimation(animateUpBottom);

            mTopLine.setVisibility(View.VISIBLE);
            mBottomLine.setVisibility(View.VISIBLE);
        }
        mAnimationFlag = !mAnimationFlag;
    }



}
