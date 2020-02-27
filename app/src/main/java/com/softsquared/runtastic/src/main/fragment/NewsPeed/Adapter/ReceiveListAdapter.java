package com.softsquared.runtastic.src.main.fragment.NewsPeed.Adapter;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.softsquared.runtastic.R;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.services.FriendAlarmActivityService;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.interfaces.FriendAlarmActivityView;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.models.RequestNumber;

import java.util.ArrayList;

public class ReceiveListAdapter extends BaseAdapter implements FriendAlarmActivityView {
    ArrayList<ReceiveFriendItem> mList = new ArrayList<>();

    Context context;
    int layout;
    LayoutInflater inf;

    public ReceiveListAdapter(ArrayList<ReceiveFriendItem> mList, Context context, int layout) {
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
    public ReceiveFriendItem getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView==null) {
            convertView = inf.inflate(layout, null);
        }
        ImageView profileImg = convertView.findViewById(R.id.alarm_list_item_profile_img);
        TextView name = convertView.findViewById(R.id.alarm_list_item_name);
        ImageButton btnAccept = convertView.findViewById(R.id.alarm_list_btn_add);
        profileImg.setBackground(new ShapeDrawable(new OvalShape()));
        profileImg.setClipToOutline(true);


        final ReceiveFriendItem item = mList.get(position);
        String nameStr = item.getFirstName() + " " + item.getLastName();

        Glide.with(convertView.getContext()).load(item.getProfileImage()).into(profileImg);
        name.setText(nameStr);

        final RequestNumber number = new RequestNumber(Integer.parseInt(item.getRequestNo()));
        final FriendAlarmActivityService service = new FriendAlarmActivityService(this);


        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.acceptOk(number);
                mList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context,"추가 되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    @Override
    public void validateSuccess(String message) {
        Log.e("[Log.e] validateSuccess",message);
    }

    @Override
    public void validateFailure(String text) {

    }

    @Override
    public void getArrayReceive(ArrayList<ReceiveFriendItem> result) {

    }
}
