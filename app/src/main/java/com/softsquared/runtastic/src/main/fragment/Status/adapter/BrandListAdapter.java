package com.softsquared.runtastic.src.main.fragment.Status.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.softsquared.runtastic.R;

import java.util.ArrayList;

public class BrandListAdapter extends BaseAdapter {
    ArrayList<BrandListItem> mList = new ArrayList<>();

    Context context;
    int layout;
    LayoutInflater inf;

    public BrandListAdapter(ArrayList<BrandListItem> mList, Context context, int layout) {
        this.mList = mList;
        this.context = context;
        this.layout = layout;
        this.inf = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public BrandListItem getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null) {
            convertView = inf.inflate(layout, null);
        }
        BrandListItem item = mList.get(position);
        TextView tvBrandName = convertView.findViewById(R.id.brand_list_item_brand_name);

        tvBrandName.setText(item.getBrandName());

        return convertView;
    }
}
