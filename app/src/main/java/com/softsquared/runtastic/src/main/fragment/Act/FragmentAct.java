package com.softsquared.runtastic.src.main.fragment.Act;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.main.fragment.Act.adapter.StopPagerAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FragmentAct extends Fragment implements OnMapReadyCallback {
    private int ACT_MODE_START = 1;
    private int ACT_MODE_CANCEL = 2;

    TextView mTvHours, mTvMinutes, mTvSeconds;
    Timer mTimerAct;
    TimerTask mTimerTaskAct;

    private GridLayout mGridExercise;
    private LinearLayout mLinearTop, mLinearBottom;
    private View mTopLine, mBottomLine;
    BottomNavigationView mBottomNav;
    ImageButton mBtnMusic, mBtnSetting;
    Button mBtnStart;
    int REQUEST_OK = 1;
    int REQUEST_SET_ACT = 2;

    // 뷰페이저
    StopPagerAdapter mStopPagerAdapter;
    ViewPager mVpStop;
    Button mBtnComplete, mBtnContinue;

    // 애니메이션
    boolean mAnimationFlag;
    boolean mStartAct;
    boolean mBanedMapClick = false;
    boolean mDrawLine = false;
    TranslateAnimation animateDownBottom;
    TranslateAnimation animateUpBottom;
    TranslateAnimation animateUpTop;
    TranslateAnimation animateDownTop;
    TranslateAnimation animateSlideRightToRight;
    TranslateAnimation animateSlideRightToLeft;
    TranslateAnimation animateSlideLeftToRight;
    TranslateAnimation animateSlideLeftToLeft;


    // 지도쪽 변수들
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
    private static final int FASTEST_UPDATE_INTERVAL_MS = 1000 * 1; // 화면 갱신 주기
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";


    // 선그리고 거리계산
    private LatLng startLatLng = new LatLng(0, 0);
    private LatLng endLatLng = new LatLng(0, 0);
    private List<Polyline> polylines = new ArrayList<>();
    private double mMoveDistance = 0;
    private double mDistanceByKm = 0;
    TextView mTvDistance;
    TextView mTvCalories;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_act, container, false);

        mStartAct = false;
        locationRequest = new LocationRequest()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY) // 정확도를 최우선적으로 고려
                .setInterval(UPDATE_INTERVAL_MS) // 위치가 update 되는 주기
                .setFastestInterval(FASTEST_UPDATE_INTERVAL_MS); // 위치 획득후 업데이트 되는 주기

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.act_map);
        mapFragment.getMapAsync(this);

        mTvDistance = rootView.findViewById(R.id.act_tv_distance);
        mTvCalories = rootView.findViewById(R.id.act_tv_calorie);

        mTvHours = rootView.findViewById(R.id.act_tv_hours);
        mTvMinutes = rootView.findViewById(R.id.act_tv_minutes);
        mTvSeconds = rootView.findViewById(R.id.act_tv_seconds);

        mGridExercise = rootView.findViewById(R.id.act_gl_time);
        mLinearTop = rootView.findViewById(R.id.act_ll_top_bar);
        mLinearBottom = rootView.findViewById(R.id.act_ll_bottom_bar);
        mTopLine = rootView.findViewById(R.id.act_v_top_line);
        mBtnMusic = rootView.findViewById(R.id.act_btn_music);
        mBtnSetting = rootView.findViewById(R.id.act_btn_setting);
        mBtnStart = rootView.findViewById(R.id.act_btn_start);
        mBottomLine = getActivity().findViewById(R.id.main_v_bottom_line);
        mBottomNav = getActivity().findViewById(R.id.main_bottom_nav_bar);

        mVpStop = rootView.findViewById(R.id.act_vp_pager_stop);
        mStopPagerAdapter = new StopPagerAdapter(getActivity().getSupportFragmentManager());
        mVpStop.setAdapter(mStopPagerAdapter);
        mVpStop.setCurrentItem(1, true);

        setButtonTools();
        createAnimation();

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
                if (!mBanedMapClick) {
                    createAnimation();
                    animateMapClick();
                }
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
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void setDefaultLocation() {
        if (currentMarker != null) currentMarker.remove();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(mDefaultLocation);
        markerOptions.title(getString(R.string.gps_error));
        markerOptions.snippet(getString(R.string.gps_error_explain));
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        currentMarker = mGoogleMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mDefaultLocation, 18);
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
                if (mDrawLine) { // 운동 시작
                    endLatLng = new LatLng(location.getLatitude(), location.getLongitude());        //현재 위치를 끝점으로 설정
                    drawPath();
                    startLatLng = new LatLng(location.getLatitude(), location.getLongitude());        //시작점을 끝점으로 다시 설정
                }
            }
        }
    };

    private String CurrentTime() {
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

        //currentMarker = mGoogleMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(currentLatLng);
        mGoogleMap.moveCamera(cameraUpdate);
    }


    private void getDeviceLocation() {
        try {
            if (mLocationPermissionGranted) {
                mFusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
            }
        } catch (SecurityException e) {
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
            if (mGoogleMap != null)
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

    private void drawPath() {
        PolylineOptions options = new PolylineOptions().add(startLatLng).add(endLatLng).width(15).color(R.color.colorPrimary).geodesic(true);
        polylines.add(mGoogleMap.addPolyline(options));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startLatLng, 18));

        mMoveDistance += getDistance(startLatLng, endLatLng);
        mDistanceByKm = mMoveDistance * 0.001;
        int calorie = (int) mMoveDistance / 30;
        String disString = String.format("%.2f", mDistanceByKm);
        mTvDistance.setText(disString);
        mTvCalories.setText(Integer.toString(calorie));
        // Log.e("mMoveDistance"," is " + mMoveDistance + " Km : " + disString);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_OK) {
            switch (data.getIntExtra("mode_flag", ACT_MODE_CANCEL)) {
                case 1: // 운동 시작
                    mVpStop.setVisibility(View.VISIBLE); // 뷰페이저 보이게
                    mBanedMapClick = true;
                    setTimerStart();
                    mDrawLine = true;
                    startLatLng = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
                    break;
                case 2: // 카운트다운에서 취소버튼 눌렀을 때
                    mStartAct = true;
                    animateStart();
                    break;
            }
        } else if(requestCode == REQUEST_SET_ACT) {
            switch (data.getIntExtra("setAct",1)) {
                case 1:
                    mBtnStart.setText(getString(R.string.act_set_running));
                    break;
                case 2:
                    mBtnStart.setText(getString(R.string.act_set_walking));
                    break;
                case 3:
                    mBtnStart.setText(getString(R.string.act_set_hiking));
                    break;
                case 4:
                    mBtnStart.setText(getString(R.string.act_set_cycling));
                    break;
                default:
                    break;
            }
        }


    }

    public void setButtonTools() {
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAnimation();
                animateStart();
                Intent intent = new Intent(getActivity().getApplicationContext(), CountDownActivity.class);
                intent.putExtra("StartFlag", mStartAct);
                startActivityForResult(intent, REQUEST_OK);
                getActivity().overridePendingTransition(R.anim.fade_in_animation, R.anim.fade_out_animation);
            }
        });
        mBtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ActSettingActivity.class);
                startActivityForResult(intent,REQUEST_SET_ACT);
            }
        });
    }


    public void clickedComplete() { // 운동 끝났을때 완료했을때
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("완료하셨나요?").setMessage("활동을 저장하시겠습니까 지우시겠습니까 ?");

        builder.setPositiveButton("저장", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                //Toast.makeText(getContext(), "OK Click", Toast.LENGTH_SHORT).show();
                mVpStop.setVisibility(View.GONE);
                mVpStop.setCurrentItem(1);
                mBanedMapClick = false; // 이게 false 일때만 맵클릭 시 애니메이션
                animateStart();
                setTimerStop();
                mDrawLine = false;
                mGoogleMap.clear();
                polylines.clear();
                mMoveDistance = 0;

            }
        });

        builder.setNegativeButton("삭제", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                //Toast.makeText(getContext(), "Cancel Click", Toast.LENGTH_SHORT).show();
                mVpStop.setVisibility(View.GONE);
                mVpStop.setCurrentItem(1);
                mBanedMapClick = false; // 이게 false 일때만 맵클릭 시 애니메이션
                animateStart();
                setTimerStop();
                mDrawLine = false;
                mGoogleMap.clear();
                polylines.clear();
                mMoveDistance = 0;
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void clickedContinue() { // 계속하기
        mVpStop.setCurrentItem(1, true);
    }

    private void setTimerStart() {
        mTimerAct = new Timer();
        mTimerTaskAct = new TimerTask() {
            int seconds = 0;
            int minutes = 0;
            int hours = 0;

            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        seconds++;
                        if (seconds < 10) {
                            mTvSeconds.setText("0" + seconds);
                        } else if (seconds < 60) {
                            mTvSeconds.setText(Integer.toString(seconds));
                        } else {
                            mTvSeconds.setText(getString(R.string.exercise_time));
                            minutes++;
                            seconds = 0;
                            if (minutes < 10) {
                                mTvMinutes.setText("0" + minutes);
                            } else if (minutes < 60) {
                                mTvMinutes.setText(Integer.toString(minutes));
                            } else {
                                mTvMinutes.setText(getString(R.string.exercise_time));
                                hours++;
                                minutes = 0;
                                if (hours < 10) {
                                    mTvHours.setText("0" + hours);
                                } else {
                                    mTvHours.setText(Integer.toString(hours));
                                }
                            }
                        }
                    }
                });
            }

            @Override
            public boolean cancel() {
                return super.cancel();
            }
        };
        mTimerAct.schedule(mTimerTaskAct, 0, 1000);
    }

    private void setTimerStop() {
        if (mTimerTaskAct != null) {
            mTimerTaskAct.cancel();
            mTvSeconds.setText(getString(R.string.exercise_time));
            mTvMinutes.setText(getString(R.string.exercise_time));
            mTvHours.setText(getString(R.string.exercise_time));
            mTvDistance.setText(getString(R.string.exercise_distance));
            mTvCalories.setText(getString(R.string.exercise_calorie));
            mTimerTaskAct = null;
        }
    }

    public double getDistance(LatLng LatLng1, LatLng LatLng2) {
        double distance = 0;
        Location locationA = new Location("A");
        locationA.setLatitude(LatLng1.latitude);
        locationA.setLongitude(LatLng1.longitude);
        Location locationB = new Location("B");
        locationB.setLatitude(LatLng2.latitude);
        locationB.setLongitude(LatLng2.longitude);
        distance = locationA.distanceTo(locationB);

        return distance;
    }


    public void createAnimation() {
        animateDownBottom = new TranslateAnimation(0, 0, 0, mLinearBottom.getHeight() + mBottomNav.getHeight() + 45);
        animateUpBottom = new TranslateAnimation(0, 0, mLinearBottom.getHeight() + mBottomNav.getHeight() + 45, 0);
        animateUpTop = new TranslateAnimation(0, 0, 0, -mGridExercise.getHeight() - mLinearTop.getHeight());
        animateDownTop = new TranslateAnimation(0, 0, -mGridExercise.getHeight() - mLinearTop.getHeight(), 0);
        animateSlideRightToRight = new TranslateAnimation(0, mBtnMusic.getWidth() + 50, 0, 0);
        animateSlideRightToLeft = new TranslateAnimation(mBtnMusic.getWidth() + 50, 0, 0, 0);
        animateSlideLeftToRight = new TranslateAnimation(-mBtnMusic.getWidth() - 50, 0, 0, 0);
        animateSlideLeftToLeft = new TranslateAnimation(0, -mBtnMusic.getWidth() - 50, 0, 0);
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
    }

    public void animateStart() {
        if (!mStartAct) {
            mBottomNav.startAnimation(animateDownBottom);
            mBottomNav.getMenu().getItem(0).setVisible(false);
            mBottomNav.getMenu().getItem(1).setVisible(false);
            mBottomNav.getMenu().getItem(2).setVisible(false);
            mBottomNav.getMenu().getItem(3).setVisible(false);
            mBottomNav.getMenu().getItem(4).setVisible(false);
            mBottomNav.setVisibility(View.GONE);


            mBtnStart.startAnimation(animateDownBottom);
            mBtnStart.setVisibility(View.VISIBLE);
            mBtnStart.setClickable(false);

            mBtnSetting.setVisibility(View.VISIBLE);
            mBtnSetting.startAnimation(animateSlideRightToRight);
            mBtnSetting.setClickable(false);

            mBtnMusic.setVisibility(View.VISIBLE);
            mBtnMusic.startAnimation(animateSlideLeftToLeft);
            mBtnMusic.setClickable(false);

            mBottomLine.setVisibility(View.INVISIBLE);
        } else {
            mBottomNav.startAnimation(animateUpBottom);
            mBottomNav.getMenu().getItem(0).setVisible(true);
            mBottomNav.getMenu().getItem(1).setVisible(true);
            mBottomNav.getMenu().getItem(2).setVisible(true);
            mBottomNav.getMenu().getItem(3).setVisible(true);
            mBottomNav.getMenu().getItem(4).setVisible(true);
            mBottomNav.setVisibility(View.VISIBLE);

            mBtnStart.setVisibility(View.INVISIBLE);
            mBtnStart.startAnimation(animateUpBottom);
            mBtnStart.setClickable(true);

            mBtnSetting.setVisibility(View.INVISIBLE);
            mBtnSetting.startAnimation(animateSlideRightToLeft);
            mBtnSetting.setClickable(true);

            mBtnMusic.setVisibility(View.INVISIBLE);
            mBtnMusic.startAnimation(animateSlideLeftToRight);
            mBtnMusic.setClickable(true);
        }
        mStartAct = !mStartAct;
    }

    public void animateMapClick() {
        System.out.println("상단바: " + mLinearTop.getHeight() + " , 그리드레이아웃 : " + mGridExercise.getHeight() +
                " , 시작버튼 : " + mLinearBottom.getHeight() + ", 바텀 nav : " + mBottomNav.getHeight());
        if (!mAnimationFlag) {
            mGridExercise.setVisibility(View.VISIBLE);
            mGridExercise.startAnimation(animateUpTop);

            mBottomNav.startAnimation(animateDownBottom);
            mBottomNav.setVisibility(View.VISIBLE);
            mBottomNav.getMenu().getItem(0).setEnabled(false);
            mBottomNav.getMenu().getItem(1).setEnabled(false);
            mBottomNav.getMenu().getItem(2).setEnabled(false);
            mBottomNav.getMenu().getItem(3).setEnabled(false);
            mBottomNav.getMenu().getItem(4).setEnabled(false);

            mBtnStart.startAnimation(animateDownBottom);
            mBtnStart.setVisibility(View.VISIBLE);
            mBtnStart.setClickable(false);

            mTopLine.setVisibility(View.INVISIBLE);
            mBottomLine.setVisibility(View.INVISIBLE);

            mBtnSetting.setVisibility(View.VISIBLE);
            mBtnSetting.startAnimation(animateSlideRightToRight);
            mBtnSetting.setClickable(false);

            mBtnMusic.setVisibility(View.VISIBLE);
            mBtnMusic.startAnimation(animateSlideLeftToLeft);
            mBtnMusic.setClickable(false);
        } else {
            mGridExercise.setVisibility(View.INVISIBLE);
            mGridExercise.startAnimation(animateDownTop);

            mBtnStart.setVisibility(View.INVISIBLE);
            mBtnStart.startAnimation(animateUpBottom);
            mBtnStart.setClickable(true);


            mBottomNav.setVisibility(View.INVISIBLE);
            mBottomNav.startAnimation(animateUpBottom);
            mBottomNav.getMenu().getItem(0).setEnabled(true);
            mBottomNav.getMenu().getItem(1).setEnabled(true);
            mBottomNav.getMenu().getItem(2).setEnabled(true);
            mBottomNav.getMenu().getItem(3).setEnabled(true);
            mBottomNav.getMenu().getItem(4).setEnabled(true);

            mTopLine.setVisibility(View.VISIBLE);
            mBottomLine.setVisibility(View.VISIBLE);

            mBtnSetting.setVisibility(View.INVISIBLE);
            mBtnSetting.startAnimation(animateSlideRightToLeft);
            mBtnSetting.setClickable(true);

            mBtnMusic.setVisibility(View.INVISIBLE);
            mBtnMusic.startAnimation(animateSlideLeftToRight);
            mBtnMusic.setClickable(true);
        }
        mAnimationFlag = !mAnimationFlag;
    }


}
