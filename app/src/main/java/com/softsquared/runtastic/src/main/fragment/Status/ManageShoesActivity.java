package com.softsquared.runtastic.src.main.fragment.Status;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.main.fragment.Status.adapter.SneakersListAdapter;
import com.softsquared.runtastic.src.main.fragment.Status.adapter.SneakersListItem;
import com.softsquared.runtastic.src.main.fragment.Status.interfaces.ManageShoesActivityView;
import com.softsquared.runtastic.src.main.fragment.Status.services.ManageShoesActivityService;

import java.util.ArrayList;

public class ManageShoesActivity extends BaseActivity implements ManageShoesActivityView {
    ArrayList<SneakersListItem> mSneakersArray = new ArrayList<>();

    ListView mListViewSneakers;
    SneakersListAdapter adapter;

    ImageButton mBtnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_shoes);
        showProgressDialog();
        mListViewSneakers = findViewById(R.id.manage_shoes_list_view);
        mBtnBack = findViewById(R.id.manage_shoes_back);
        final ManageShoesActivityService service = new ManageShoesActivityService(this);
        service.getArrayUserSneakers();


        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void validateSuccess(String message) {
        hideProgressDialog();
    }

    @Override
    public void validateFailure(String text) {
        hideProgressDialog();
    }

    @Override
    public void getArrayShoes(ArrayList<SneakersListItem> result) {
        mSneakersArray = result;
        adapter = new SneakersListAdapter(mSneakersArray,getApplicationContext(),R.layout.manage_shoes_list_item);
        mListViewSneakers.setAdapter(adapter);
        hideProgressDialog();
    }
}
