package com.example.myflowerproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.myflowerproject.R;
import com.example.myflowerproject.fragment.ResetPassword;
import com.example.myflowerproject.model.adapter.NotificationAdapter;
import com.example.myflowerproject.model.entity.NotificationModel;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {
    ListView lvNotifi ;
    ArrayList<NotificationModel> arrNotifi;
    ConstraintLayout cons1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        //ConstraintLayout cons1;
        Toolbar toolbar = findViewById(R.id.toolbar3);
        cons1= findViewById(R.id.cons1);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        lvNotifi = findViewById(R.id.lvNotifi);
        arrNotifi = new ArrayList<NotificationModel>();
        arrNotifi.add(new NotificationModel("Chấp nhận yêu cầu hủy đơn","Yêu cầu hủy đơn hàng của bạn đã được chấp nhận.Đơn hàng đã được hủy thành công",R.mipmap.doc_hong_bk33 ,"07:20","20-1-2020"));
        arrNotifi.add(new NotificationModel("Chấp nhận yêu cầu hủy đơn","Yêu cầu hủy đơn hàng của bạn đã được chấp nhận.Đơn hàng đã được hủy thành công ", R.mipmap.doc_cam_bk3 ,"07:20","20-1-2020"));
        arrNotifi.add(new NotificationModel("Chia sẻ và nhận xét về sản phẩm","Đơn hàng của bạn đã được hoàn thành.Bạn hãy đánh giá sản phẩm để giúp người dùng khác hiểu hơn về sản phẩm nhé!",R.mipmap.doc_do_bk32 , "07:20","20-1-2020"));
        arrNotifi.add(new NotificationModel("Chia sẻ và nhận xét về sản phẩm","Đơn hàng của bạn đã được hoàn thành.Bạn hãy đánh giá sản phẩm để giúp người dùng khác hiểu hơn về sản phẩm nhé!",R.mipmap.doc_do_bk34 , "07:20","20-1-2020"));
        arrNotifi.add(new NotificationModel("Chấp nhận yêu cầu hủy đơn","Yêu cầu hủy đơn hàng của bạn đã được chấp nhận.Đơn hàng đã được hủy thành công",R.mipmap.doc_vang_bk4 , "07:20","20-1-2020"));
        NotificationAdapter adapter = new NotificationAdapter(
                Notification.this,
                R.layout.lines_notification,
                arrNotifi
        );
        lvNotifi.setAdapter(adapter);
        lvNotifi.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                lvNotifi.setBackgroundColor(R.color.colorAccent);
//                Intent cart_intent = new Intent(Notification.this, ResetPassword.class);
//                startActivity(cart_intent);
                //lvNotifi.setBackgroundColor(Color.parseColor("#rrggbb"));
                //lvNotifi.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                lvNotifi.getChildAt(position).setBackgroundColor(Color.parseColor("#FFFFFF"));
//                n++;
//                if(n!=0){
//                    lvNotifi.getChildAt(position).setBackgroundColor(Color.parseColor("#FFFFFF"));
//                }
            }

        });

        //thay đổi hiệu ứng khi click vào thông báo
//        cons1 = findViewById(R.id.cons1);
//        cons1.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                //cons1.setBackgroundColor(Color.WHITE);
//                cons1.setBackgroundColor(getResources().getColor(android.R.color.white));
//            }
//        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

}
