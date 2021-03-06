package com.softsquared.runtastic.src.main.fragment.NewsPeed;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.interfaces.FindFriendActivityView;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.models.AddFriendRequest;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.models.FindFriendResponse;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.services.FindFriendActivityService;

public class FindFriendActivity extends BaseActivity implements FindFriendActivityView {
    EditText mEtFindEmail;
    LinearLayout mLlProfileCard;
    TextView mTvName,mTvNoFind;
    Button mBtnAdd;
    ImageView mProfileImg;

    int mUserNo = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);

        mEtFindEmail = findViewById(R.id.find_friends_et_find_email);
        mLlProfileCard = findViewById(R.id.find_friends_ll_profile_card);
        mTvName = findViewById(R.id.find_friends_tv_name);
        mTvNoFind = findViewById(R.id.find_friends_tv_no_find);
        mBtnAdd = findViewById(R.id.find_friends_btn_add);
        mProfileImg = findViewById(R.id.find_friends_iv_profile_img);

        setEditTextListener();
    }

    public void customOnClickInFindFriend(View v) {
        switch (v.getId()) {
            case R.id.find_friends_btn_back:
                finish();
                break;
            case R.id.find_friends_btn_add:
                Log.e("[Log.e] mUserNo is:", mUserNo + " ");
                tryAddFriend(mUserNo);
                break;
            default:
                break;
        }
    }

    private void setEditTextListener() {
        mEtFindEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_DONE :
                        //showCustomToast("email input : " + mEtFindEmail.getText().toString());
                        trySearch(mEtFindEmail.getText().toString());
                        return false;
                    default:
                        return false;
                }
            }
        });
    }

    //-------------통신 부분-----------------
    private void trySearch(String email) {
        showProgressDialog();
        final FindFriendActivityService service = new FindFriendActivityService(this);
        service.getFriend(email);
    }

    private void tryAddFriend(int userNo) {
        showProgressDialog();
        final FindFriendActivityService service = new FindFriendActivityService(this);
        AddFriendRequest request = new AddFriendRequest(userNo);
        service.postRequestFriend(request);
    }

    @Override
    public void getFriendsInfo(FindFriendResponse.FriendResult result) {
        mLlProfileCard.setVisibility(View.VISIBLE);
        mProfileImg.setBackground(new ShapeDrawable(new OvalShape()));
        mProfileImg.setClipToOutline(true);
        mTvNoFind.setVisibility(View.INVISIBLE);
        mBtnAdd.setClickable(true);
        mBtnAdd.setText(getString(R.string.find_friends_add));
        mTvName.setText(result.getFirstName() + " " + result.getLastName());
        Glide.with(getApplicationContext()).load(result.getProfileImage()).into(mProfileImg);
        mUserNo = Integer.parseInt(result.getUserNo());
        hideProgressDialog();
    }

    @Override
    public void sendNoInfo() {
        mLlProfileCard.setVisibility(View.GONE);
        mTvNoFind.setVisibility(View.VISIBLE);
        hideProgressDialog();
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public void validateSuccess(String text, int code) {
        hideProgressDialog();
        Log.e("[Log.e] 친구 요청 성공?", " message : " + text + " code : " + code);
        mBtnAdd.setText("요청 대기중");
        mBtnAdd.setClickable(false);
    }
}
