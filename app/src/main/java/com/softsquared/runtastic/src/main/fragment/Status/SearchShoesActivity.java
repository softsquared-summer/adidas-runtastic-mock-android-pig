package com.softsquared.runtastic.src.main.fragment.Status;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.main.fragment.Status.adapter.BrandListAdapter;
import com.softsquared.runtastic.src.main.fragment.Status.adapter.BrandListItem;
import com.softsquared.runtastic.src.main.fragment.Status.interfaces.SearchShoesActivityView;
import com.softsquared.runtastic.src.main.fragment.Status.services.SearchShoesActivityService;

import java.util.ArrayList;

public class SearchShoesActivity extends BaseActivity implements SearchShoesActivityView {
    ArrayList<BrandListItem> mBrandArray = new ArrayList<>();
    BrandListAdapter mBrandAdapter;
    ListView mListViewBrand;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_shoes);
        mListViewBrand = findViewById(R.id.search_shoes_list_brand);

        tryGetBrand();

    }

    public void customOnClickInSearchShoes(View v) {
        switch (v.getId()) {
            case R.id.search_shoes_cancel:
                finish();
                break;
            default:
                break;
        }
    }

    public void tryGetBrand() {
        showProgressDialog();
        final SearchShoesActivityService service = new SearchShoesActivityService(this);
        service.getArrayBrand();
    }

    @Override
    public void getArrayBrand(ArrayList<BrandListItem> result) {
        mBrandArray = result;
        mBrandAdapter = new BrandListAdapter(mBrandArray,getApplicationContext(),R.layout.brand_list_item);
        mListViewBrand.setAdapter(mBrandAdapter);

        hideProgressDialog();
    }
}
