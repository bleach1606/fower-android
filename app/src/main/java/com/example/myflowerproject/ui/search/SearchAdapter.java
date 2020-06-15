package com.example.myflowerproject.ui.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myflowerproject.R;
import com.example.myflowerproject.model.api.GetImage;
import com.example.myflowerproject.model.entity.Notification;
import com.example.myflowerproject.ui.notification.NotificationAdapter;

import java.util.List;

public class SearchAdapter extends BaseAdapter {
    Context myContext;
    int myLayout;
    List<Notification> list;

    public SearchAdapter(Context myContext, int myLayout, List<Notification> list) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtTrangThai, txtNotification, txtDate;
        ImageView imgFlower;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        if (view == null || view.getTag() == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(myLayout, null);
            holder.txtTrangThai = view.findViewById(R.id.txtTrangThai);
            holder.txtNotification = view.findViewById(R.id.txtNotification);
            holder.imgFlower = view.findViewById(R.id.imgFlower);
        } else {
            holder = (ViewHolder)view.getTag();
        }
        holder.txtTrangThai.setText(list.get(position).getTitle());
        holder.txtNotification.setText(list.get(position).getContent());
        new GetImage(holder.imgFlower).execute(""+list.get(position).getImage());;
        return view;
    }
}