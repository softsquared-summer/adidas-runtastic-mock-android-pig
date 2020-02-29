package com.softsquared.runtastic.src.main.fragment.Status;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.BaseActivity;
import com.softsquared.runtastic.src.main.fragment.Status.adapter.BrandListAdapter;
import com.softsquared.runtastic.src.main.fragment.Status.adapter.BrandListItem;
import com.softsquared.runtastic.src.main.fragment.Status.adapter.ShoesItem;
import com.softsquared.runtastic.src.main.fragment.Status.adapter.ShoesListAdapter;
import com.softsquared.runtastic.src.main.fragment.Status.interfaces.SearchShoesActivityView;
import com.softsquared.runtastic.src.main.fragment.Status.models.Sneakers;
import com.softsquared.runtastic.src.main.fragment.Status.services.SearchShoesActivityService;
import com.softsquared.runtastic.src.main.fragment.Status.shoesPlus.AddShoes1Step;

import java.util.ArrayList;

public class SearchShoesActivity extends BaseActivity implements SearchShoesActivityView {

    // 브랜드 배열
    ArrayList<BrandListItem> mBrandArray = new ArrayList<>();
    ArrayList<BrandListItem> searchTemp = new ArrayList<>();

    // 모델 배열
    ArrayList<ShoesItem> mModelArray = new ArrayList<>();


    // 브랜드 리스트뷰
    BrandListAdapter mBrandAdapter;
    ListView mListViewBrand;

    // 모델 리스트뷰
    ShoesListAdapter mModelAdapter;
    ListView mListViewModel;


    EditText mEtSearchBrand;

    String mBrandName, mModelName, mImageUrl;
    Sneakers mSneakers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_shoes);
        mListViewBrand = findViewById(R.id.search_shoes_list_brand);
        mEtSearchBrand = findViewById(R.id.search_shoes_search_bar);
        mListViewModel = findViewById(R.id.search_shoes_list_model);
        mSneakers = new Sneakers();

        setSearchBar();
        tryGetBrand();

        mListViewBrand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String brandNo = mBrandArray.get(position).getBrandNo();
                mBrandName = mBrandArray.get(position).getBrandName();
                tryGetModels(brandNo);
            }
        });

        mListViewModel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mModelName = mModelArray.get(position).getModelName();
                mImageUrl = mModelArray.get(position).getImageUrl();
                mSneakers.setModelNo(Integer.parseInt(mModelArray.get(position).getModelNo()));

                Intent intent = new Intent(getApplicationContext(), AddShoes1Step.class);
                intent.putExtra("brandName", mBrandName);
                intent.putExtra("modelName", mModelName);
                intent.putExtra("sneakers", mSneakers);
                intent.putExtra("shoesImage",mImageUrl);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_animation, R.anim.fade_out_animation);
            }
        });
    }

    public void setSearchBar() {
        mEtSearchBrand.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = mEtSearchBrand.getText().toString();
                searchBrand(text);
            }
        });
    }

    public void searchBrand(String charText) {
        Log.e("searchBrand", "is satrt");
        searchTemp.clear();

        if (charText.length() == 0) {
            Log.e("charText.length() ", "" + charText.length());
            searchTemp.addAll(mBrandArray);
        } else {
            Log.e("mBrandArray.size() ", "" + mBrandArray.size());
            for (int i = 0; i < mBrandArray.size(); i++) {
                if (mBrandArray.get(i).getBrandName().toLowerCase().contains(charText)) {
                    searchTemp.add(mBrandArray.get(i));
                    Log.e("contain str", mBrandArray.get(i).getBrandName());
                } else {
                    Log.e("!contain str", mBrandArray.get(i).getBrandName());

                }
            }
        }
        mBrandAdapter.notifyDataSetChanged();
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

    public void tryGetModels(String brandNo) {
        showProgressDialog();
        final SearchShoesActivityService service = new SearchShoesActivityService(this);
        service.getArrayModel(brandNo);
    }

    @Override
    public void getArrayBrand(ArrayList<BrandListItem> result) {
        mBrandArray = result;
        searchTemp.addAll(mBrandArray);
        mBrandAdapter = new BrandListAdapter(searchTemp, getApplicationContext(), R.layout.brand_list_item);
        mListViewBrand.setAdapter(mBrandAdapter);

        hideProgressDialog();
    }

    @Override
    public void getArrayModel(ArrayList<ShoesItem> result) {
        mListViewBrand.setVisibility(View.GONE);
        mListViewModel.setVisibility(View.VISIBLE);
        mModelArray = result;
        mModelAdapter = new ShoesListAdapter(mModelArray, getApplicationContext(), R.layout.fragment_status_my_shoes_item);
        mListViewModel.setAdapter(mModelAdapter);

        hideProgressDialog();
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
    }
}
