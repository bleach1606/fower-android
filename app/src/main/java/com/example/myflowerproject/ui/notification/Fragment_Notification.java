package com.example.myflowerproject.ui.notification;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myflowerproject.R;

import java.util.ArrayList;


public class Fragment_Notification extends Fragment {
    public Fragment_Notification() {

    }
    ListView lvNotifi ;
    ArrayList<NotificationModel> arrNotifi;
    ConstraintLayout cons1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment__notification, container, false);


        lvNotifi = view.findViewById(R.id.lvNotifi);
        arrNotifi = new ArrayList<NotificationModel>();
        arrNotifi.add(new NotificationModel("Chấp nhận yêu cầu hủy đơn","Yêu cầu hủy đơn hàng của bạn đã được chấp nhận.Đơn hàng đã được hủy thành công",R.mipmap.doc_hong_bk33 ,"07:20","20-1-2020"));
        arrNotifi.add(new NotificationModel("Chấp nhận yêu cầu hủy đơn","Yêu cầu hủy đơn hàng của bạn đã được chấp nhận.Đơn hàng đã được hủy thành công ", R.mipmap.doc_cam_bk3 ,"07:20","20-1-2020"));
        arrNotifi.add(new NotificationModel("Chia sẻ và nhận xét về sản phẩm","Đơn hàng của bạn đã được hoàn thành.Bạn hãy đánh giá sản phẩm để giúp người dùng khác hiểu hơn về sản phẩm nhé!",R.mipmap.doc_do_bk32 , "07:20","20-1-2020"));
        arrNotifi.add(new NotificationModel("Chia sẻ và nhận xét về sản phẩm","Đơn hàng của bạn đã được hoàn thành.Bạn hãy đánh giá sản phẩm để giúp người dùng khác hiểu hơn về sản phẩm nhé!",R.mipmap.doc_do_bk34 , "07:20","20-1-2020"));
        arrNotifi.add(new NotificationModel("Chấp nhận yêu cầu hủy đơn","Yêu cầu hủy đơn hàng của bạn đã được chấp nhận.Đơn hàng đã được hủy thành công",R.mipmap.doc_vang_bk4 , "07:20","20-1-2020"));
        NotificationAdapter adapter = new NotificationAdapter(getContext(), R.layout.lines_notification, arrNotifi);
        lvNotifi.setAdapter(adapter);
        lvNotifi.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvNotifi.getChildAt(position).setBackgroundColor(Color.parseColor("#FFFFFF"));
            }

        });



        return view;

    }

}
