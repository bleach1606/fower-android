package com.example.myflowerproject.ui.notification;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.myflowerproject.R;
import com.example.myflowerproject.model.adapter.NotificationAdapter;
import com.example.myflowerproject.model.entity.NotificationModel;
import com.example.myflowerproject.view.Activity_Home;
import com.example.myflowerproject.view.Notification;

import java.util.ArrayList;

public class Fragment_Notification extends Fragment {
    ListView lvNotifi ;
    ArrayList<NotificationModel> arrNotifi;
    ImageButton btnBack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment__notification, container, false);
//        btnBack = view.findViewById(R.id.btnBack);
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), Activity_Home.class);
//                startActivity(intent);
//            }
//        });
        lvNotifi = view.findViewById(R.id.lvNotifi);
        arrNotifi = new ArrayList<NotificationModel>();
        arrNotifi.add(new NotificationModel("Chấp nhận yêu cầu hủy đơn","Yêu cầu hủy đơn hàng của bạn đã được chấp nhận.Đơn hàng đã được hủy thành công",R.mipmap.doc_hong_bk33));
        arrNotifi.add(new NotificationModel("Chấp nhận yêu cầu hủy đơn","Yêu cầu hủy đơn hàng của bạn đã được chấp nhận.Đơn hàng đã được hủy thành công ", R.mipmap.doc_hong_bk33));
        arrNotifi.add(new NotificationModel("Chia sẻ và nhận xét về sản phẩm","Đơn hàng của bạn đã được hoàn thành.Bạn hãy đánh giá sản phẩm để giúp người dùng khác hiểu hơn về sản phẩm nhé!",R.mipmap.doc_hong_bk33));
        arrNotifi.add(new NotificationModel("Chia sẻ và nhận xét về sản phẩm","Đơn hàng của bạn đã được hoàn thành.Bạn hãy đánh giá sản phẩm để giúp người dùng khác hiểu hơn về sản phẩm nhé!",R.mipmap.doc_hong_bk33));
        arrNotifi.add(new NotificationModel("Chấp nhận yêu cầu hủy đơn","Yêu cầu hủy đơn hàng của bạn đã được chấp nhận.Đơn hàng đã được hủy thành công",R.mipmap.doc_hong_bk33));
        NotificationAdapter adapter = new NotificationAdapter(getContext(), R.layout.lines_notification, arrNotifi);
        lvNotifi.setAdapter(adapter);
        return view;
    }
}
