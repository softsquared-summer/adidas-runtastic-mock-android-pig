package com.softsquared.runtastic.src.main.fragment.Status;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.main.fragment.Status.adapter.RecentActivityAdapter;

public class FragmentStatus extends Fragment {
    RecyclerView mRecentActRecyclerView;
    LinearLayoutManager linearLayoutManager;
    RecentActivityAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_status , container, false);
        mRecentActRecyclerView = rootView.findViewById(R.id.fragment_status_rv_activity);
        mRecentActRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        linearLayoutManager = new LinearLayoutManager(getContext());
        mRecentActRecyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecentActivityAdapter();
        mRecentActRecyclerView.setAdapter(adapter);

        init();

        return rootView;
    }

    private void setButtonTools() {

    }

    private void init() {
        adapter.addItem(R.drawable.status_running,R.drawable.status_weather,"0.01 km","00:00:34");
        adapter.addItem(R.drawable.status_add_btn,R.drawable.profile_btn_in,"수동으로 활동 추가하기"," ");
        adapter.notifyDataSetChanged();
    }
}
