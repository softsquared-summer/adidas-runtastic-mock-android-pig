package com.softsquared.runtastic.src.main.fragment.Status.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.runtastic.R;

import java.util.ArrayList;

public class RecentActivityAdapter extends RecyclerView.Adapter<RecentActivityAdapter.ItemViewHolder> {

    ArrayList<RecentItem> recentItemArrayList = new ArrayList<>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_status_recent_act_item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.onBind(recentItemArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return recentItemArrayList.size();
    }

    public void addItem(int mIcon, int sIcon, String mTitle, String sTitle) {
        RecentItem recentItem = new RecentItem(mIcon,sIcon,mTitle,sTitle);
        recentItemArrayList.add(recentItem);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView tvMainTitle;
        private TextView tvSubTitle;
        private ImageView ivMainIcon;
        private ImageView ivSubIcon;

        ItemViewHolder(View itemView) {
            super(itemView);

            tvMainTitle = itemView.findViewById(R.id.status_recent_item_title);
            tvSubTitle = itemView.findViewById(R.id.status_recent_item_sub);
            ivMainIcon = itemView.findViewById(R.id.status_recent_item_icon);
            ivSubIcon = itemView.findViewById(R.id.status_recent_item_weather);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos == recentItemArrayList.size()-1) {
                        Log.e("pos : " , pos+" ");
                    }
                }
            });

        }

        void onBind(RecentItem recentItem) {
            tvMainTitle.setText(recentItem.getMaintitle());
            tvSubTitle.setText(recentItem.getSubtitle());
            ivMainIcon.setImageResource(recentItem.getMainIcon());
            ivSubIcon.setImageResource(recentItem.getSubIcon());
        }

    }
}
