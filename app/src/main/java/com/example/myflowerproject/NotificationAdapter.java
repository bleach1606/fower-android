package com.example.myflowerproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NotificationAdapter extends BaseAdapter {
    Context myContext;
    int myLayout;
    List<NotificationModel> arrayNotification;

    public NotificationAdapter(Context myContext, int myLayout, List<NotificationModel> arrayNotification) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.arrayNotification = arrayNotification;
    }

    @Override
    public int getCount() {
        return arrayNotification.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(myLayout,null);
        TextView txtTrangThai = convertView.findViewById(R.id.txtTrangThai);
        txtTrangThai.setText(arrayNotification.get(position).status);
        TextView txtNotification = convertView.findViewById(R.id.txtNotification);
        txtNotification.setText(arrayNotification.get(position).notifications);
        ImageView imgFlower = convertView.findViewById(R.id.imgFlower);
        imgFlower.setImageResource(arrayNotification.get(position).image);
        return convertView;
    }
}
