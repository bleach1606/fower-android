package com.example.myflowerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {
    ListView lvNotifi ;
    ArrayList<NotificationModel> arrNotifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        lvNotifi = findViewById(R.id.lvNotifi);
        arrNotifi = new ArrayList<NotificationModel>();
        arrNotifi.add(new NotificationModel("Chấp nhận yêu cầu hủy đơn","Yêu cầu hủy đơn hàng của bạn đã được chấp nhận.Đơn hàng đã được hủy thành công",R.mipmap.doc_hong_bk33));
        arrNotifi.add(new NotificationModel("Chấp nhận yêu cầu hủy đơn","Yêu cầu hủy đơn hàng của bạn đã được chấp nhận.Đơn hàng đã được hủy thành công ", R.mipmap.doc_cam_bk3));
        arrNotifi.add(new NotificationModel("Chia sẻ và nhận xét về sản phẩm","Đơn hàng của bạn đã được hoàn thành.Bạn hãy đánh giá sản phẩm để giúp người dùng khác hiểu hơn về sản phẩm nhé!",R.mipmap.doc_do_bk32));
        arrNotifi.add(new NotificationModel("Chia sẻ và nhận xét về sản phẩm","Đơn hàng của bạn đã được hoàn thành.Bạn hãy đánh giá sản phẩm để giúp người dùng khác hiểu hơn về sản phẩm nhé!",R.mipmap.doc_do_bk34));
        arrNotifi.add(new NotificationModel("Chấp nhận yêu cầu hủy đơn","Yêu cầu hủy đơn hàng của bạn đã được chấp nhận.Đơn hàng đã được hủy thành công",R.mipmap.doc_vang_bk4));
        NotificationAdapter adapter = new NotificationAdapter(
                Notification.this,
                R.layout.lines_notification,
                arrNotifi
        );
        lvNotifi.setAdapter(adapter);
    }
}
