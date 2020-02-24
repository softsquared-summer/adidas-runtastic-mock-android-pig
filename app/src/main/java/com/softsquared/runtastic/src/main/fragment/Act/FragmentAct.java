package com.softsquared.runtastic.src.main.fragment.Act;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.softsquared.runtastic.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FragmentAct extends Fragment implements OnMapReadyCallback {

    private GridLayout mGridExercise;
    private LinearLayout mLinearTop, mLinearBottom;
    private View mTopLine,mBottomLine;
    Animation mSlideUp, mSlideDown;
    BottomNavigationView mBottomNav;
    ImageButton mBtnMusic,mBtnSetting;
    Button mBtnStart;

    boolean mAnimationFlag;


    private GoogleMap mGoogleMap;
    private Marker currentMarker = null;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationRequest locationRequest;
    private Location mCurrentLocation;
    private final LatLng mDefaultLocation = new LatLng(37.56, 126.97);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int UPDATE_INTERVAL_MS = 1000 * 60 * 15;  // LOG 찍어보니 이걸 주기로 하지 않는듯
    private static final int FASTEST_UPDATE_INTERVAL_MS = 1000 * 1 ; // 30초 단위로 화면 갱신
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_act, container, false);

        locationRequest = new LocationRequest()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY) // 정확도를 최우선적으로 고려
                .setInterval(UPDATE_INTERVAL_MS) // 위치가 update 되는 주기
                .setFastestInterval(FASTEST_UPDATE_INTERVAL_MS); // 위치 획득후 업데이트 되는 주기

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.act_map);
        mapFragment.getMapAsync(this);

        mGridExercise = rootView.findViewById(R.id.act_gl_time);
        mLinearTop = rootView.findViewById(R.id.act_ll_top_bar);
        mLinearBottom = rootView.findViewById(R.id.act_ll_bottom_bar);
        mTopLine = rootView.findViewById(R.id.act_v_top_line);
        mBtnMusic = rootView.findViewById(R.id.act_btn_music);
        mBtnSetting = rootView.findViewById(R.id.act_btn_setting);
        mBtnStart = rootView.findViewById(R.id.act_btn_start);
        mBottomLine = getActivity().findViewById(R.id.main_v_bottom_line);
        mBottomNav = getActivity().findViewById(R.id.main_bottom_nav_bar);

        mSlideDown = AnimationUtils.loadAnimation(getContext(),R.anim.slide_down);

        mSlideUp = AnimationUtils.loadAnimation(getContext(),
                R.anim.slide_up);

        setButtonTools();

        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (mGoogleMap != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, mGoogleMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, mCurrentLocation);
            super.onSaveInstanceState(outState);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        setDefaultLocation(); // GPS를 찾지 못하는 장소에 있을 경우 지도의 초기 위치가 필요함.

        getLocationPermission();

        updateLocationUI();

        getDeviceLocation();

        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                animation();
            }
        });
    }

    private void updateLocationUI() {
        if (mGoogleMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mGoogleMap.setMyLocationEnabled(true);
                mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mGoogleMap.setMyLocationEnabled(false);
                mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
                mCurrentLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void setDefaultLocation() {
        if (currentMarker != null) currentMarker.remove();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(mDefaultLocation);
        markerOptions.title("위치정보 가져올 수 없음");
        markerOptions.snippet("위치 퍼미션과 GPS 활성 여부 확인하세요");
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        currentMarker = mGoogleMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mDefaultLocation, 15);
        mGoogleMap.moveCamera(cameraUpdate);
    }

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);

            List<Location> locationList = locationResult.getLocations();

            if (locationList.size() > 0) {
                Location location = locationList.get(locationList.size() - 1);

                LatLng currentPosition
                        = new LatLng(location.getLatitude(), location.getLongitude());

                String markerTitle = "title";
                String markerSnippet = "위도:" + String.valueOf(location.getLatitude())
                        + " 경도:" + String.valueOf(location.getLongitude());

                //Log.d("Time :" , CurrentTime() + " onLocationResult : " + markerSnippet); 여기가 1초마다 실행돼

                // Update 주기를 확인해보려고 시간을 찍어보았음.
                //현재 위치에 마커 생성하고 이동
                setCurrentLocation(location, markerTitle, markerSnippet);
                mCurrentLocation = location;
            }
        }
    };

    private String CurrentTime(){
        Date today = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
        return time.format(today);
    }

    public void setCurrentLocation(Location location, String markerTitle, String markerSnippet) {
        if (currentMarker != null) currentMarker.remove();

        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(currentLatLng);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);

        currentMarker = mGoogleMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(currentLatLng);
        mGoogleMap.moveCamera(cameraUpdate);
    }

    private void getDeviceLocation() {
        try {
            if (mLocationPermissionGranted) {
                mFusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mLocationPermissionGranted) {
            Log.d("start : ", "onStart : requestLocationUpdates");
            mFusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
            if (mGoogleMap!=null)
                mGoogleMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mFusedLocationProviderClient != null) {
            Log.d("stop : ", "onStop : removeLocationUpdates");
            mFusedLocationProviderClient.removeLocationUpdates(locationCallback);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFusedLocationProviderClient != null) {
            Log.d("destroy : ", "onDestroy : removeLocationUpdates");
            mFusedLocationProviderClient.removeLocationUpdates(locationCallback);
        }
    }

    public void setButtonTools() {
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),CountDownActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in_animation, R.anim.fade_out_animation);
            }
        });
    }


    public void animation(){
        System.out.println("상단바: " + mLinearTop.getHeight() + " , 그리드레이아웃 : " + mGridExercise.getHeight() + " , 시작버튼 : " + mLinearBottom.getHeight() + ", 바텀nav : " + mBottomNav.getHeight());
        TranslateAnimation animateDownBottom = new TranslateAnimation(0, 0, 0, mLinearBottom.getHeight()+mBottomNav.getHeight()+45);
        TranslateAnimation animateUpBottom = new TranslateAnimation(0, 0, mLinearBottom.getHeight()+mBottomNav.getHeight()+45, 0);
        TranslateAnimation animateUpTop = new TranslateAnimation(0,0,0,-mGridExercise.getHeight() - mLinearTop.getHeight());
        TranslateAnimation animateDownTop = new TranslateAnimation(0,0,-mGridExercise.getHeight() - mLinearTop.getHeight(),0);
        TranslateAnimation animateSlideRightToRight = new TranslateAnimation(0,mBtnMusic.getWidth()+50,0,0);
        TranslateAnimation animateSlideRightToLeft = new TranslateAnimation(mBtnMusic.getWidth()+50,0,0,0);
        TranslateAnimation animateSlideLeftToRight = new TranslateAnimation(-mBtnMusic.getWidth()-50,0,0,0);
        TranslateAnimation animateSlideLeftToLeft = new TranslateAnimation(0,-mBtnMusic.getWidth()-50,0,0);
        animateUpBottom.setDuration(500);
        animateUpBottom.setFillAfter(true);
        animateDownBottom.setDuration(500);
        animateDownBottom.setFillAfter(true);
        animateUpTop.setDuration(500);
        animateUpTop.setFillAfter(true);
        animateDownTop.setDuration(500);
        animateDownTop.setFillAfter(true);
        animateSlideRightToRight.setDuration(500);
        animateSlideRightToLeft.setDuration(500);
        animateSlideRightToRight.setFillAfter(true);
        animateSlideRightToLeft.setFillAfter(true);
        animateSlideLeftToLeft.setDuration(500);
        animateSlideLeftToRight.setDuration(500);
        animateSlideLeftToLeft.setFillAfter(true);
        animateSlideLeftToRight.setFillAfter(true);


        if(!mAnimationFlag){
            mGridExercise.setVisibility(View.VISIBLE);
            mGridExercise.startAnimation(animateUpTop);

            mBottomNav.setVisibility(View.VISIBLE);
            mBottomNav.startAnimation(animateDownBottom);

            mBtnStart.startAnimation(animateDownBottom);
            mBtnStart.setVisibility(View.VISIBLE);



            mTopLine.setVisibility(View.INVISIBLE);
            mBottomLine.setVisibility(View.INVISIBLE);

            mBtnSetting.setVisibility(View.VISIBLE);
            mBtnSetting.startAnimation(animateSlideRightToRight);

            mBtnMusic.setVisibility(View.VISIBLE);
            mBtnMusic.startAnimation(animateSlideLeftToLeft);

        } else {
            mGridExercise.setVisibility(View.INVISIBLE);
            mGridExercise.startAnimation(animateDownTop);

            mBtnStart.setVisibility(View.INVISIBLE);
            mBottomNav.setVisibility(View.INVISIBLE);
            mBtnStart.startAnimation(animateUpBottom);
            mBottomNav.startAnimation(animateUpBottom);

            mTopLine.setVisibility(View.VISIBLE);
            mBottomLine.setVisibility(View.VISIBLE);

            mBtnSetting.setVisibility(View.INVISIBLE);
            mBtnSetting.startAnimation(animateSlideRightToLeft);

            mBtnMusic.setVisibility(View.INVISIBLE);
            mBtnMusic.startAnimation(animateSlideLeftToRight);
        }
        mAnimationFlag = !mAnimationFlag;
    }



}
