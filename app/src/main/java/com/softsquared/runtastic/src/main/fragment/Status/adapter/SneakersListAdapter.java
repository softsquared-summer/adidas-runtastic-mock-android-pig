package com.softsquared.runtastic.src.main.fragment.Status.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.softsquared.runtastic.R;

import java.util.ArrayList;

public class SneakersListAdapter extends BaseAdapter {
    ArrayList<SneakersListItem> mList = new ArrayList<>();

    Context context;
    int layout;
    LayoutInflater inf;

    public SneakersListAdapter(ArrayList<SneakersListItem> mList, Context context, int layout) {
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
    public SneakersListItem getItem(int position) {
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
        SneakersListItem item = mList.get(position);

        ImageView icon = convertView.findViewById(R.id.manage_shoes_item_icon);
        TextView name = convertView.findViewById(R.id.manage_shoes_item_name);
        TextView distance = convertView.findViewById(R.id.manage_shoes_item_distance);

        icon.setImageResource(R.drawable.shoes_dumy);
        name.setText(item.getNickname());
        if(item.getTotalDistance() == null) {
            String disStr = "0 km / " + item.getLimitDistance() + " km";
            distance.setText(disStr);
        } else {
            String disStr = item.getTotalDistance() + " km / " + item.getLimitDistance() + " km";
            distance.setText(disStr);
        }

        return convertView;
    }
}
