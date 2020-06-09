package com.example.myflowerproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.myflowerproject.R;
import com.example.myflowerproject.model.adapter.NotificationAdapter;
import com.example.myflowerproject.model.entity.NotificationModel;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {
    ListView lvNotifi ;
    ArrayList<NotificationModel> arrNotifi;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notification.this, Activity_Home.class);
                startActivity(intent);
            }
        });
        lvNotifi = findViewById(R.id.lvNotifi);
        arrNotifi = new ArrayList<NotificationModel>();
        arrNotifi.add(new NotificationModel("Chấp nhận yêu cầu hủy đơn","Yêu cầu hủy đơn hàng của bạn đã được chấp nhận.Đơn hàng đã được hủy thành công",R.mipmap.doc_hong_bk33));
        arrNotifi.add(new NotificationModel("Chấp nhận yêu cầu hủy đơn","Yêu cầu hủy đơn hàng của bạn đã được chấp nhận.Đơn hàng đã được hủy thành công ", R.mipmap.doc_hong_bk33));
        arrNotifi.add(new NotificationModel("Chia sẻ và nhận xét về sản phẩm","Đơn hàng của bạn đã được hoàn thành.Bạn hãy đánh giá sản phẩm để giúp người dùng khác hiểu hơn về sản phẩm nhé!",R.mipmap.doc_hong_bk33));
        arrNotifi.add(new NotificationModel("Chia sẻ và nhận xét về sản phẩm","Đơn hàng của bạn đã được hoàn thành.Bạn hãy đánh giá sản phẩm để giúp người dùng khác hiểu hơn về sản phẩm nhé!",R.mipmap.doc_hong_bk33));
        arrNotifi.add(new NotificationModel("Chấp nhận yêu cầu hủy đơn","Yêu cầu hủy đơn hàng của bạn đã được chấp nhận.Đơn hàng đã được hủy thành công",R.mipmap.doc_hong_bk33));
        NotificationAdapter adapter = new NotificationAdapter(
                Notification.this,
                R.layout.lines_notification,
                arrNotifi
        );
        lvNotifi.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
            case R.id.app_bar_search:
                //to do: Search
                return true;
            case R.id.home_cart_icon:
                return true;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}
