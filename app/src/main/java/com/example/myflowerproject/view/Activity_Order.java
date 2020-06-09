package com.example.myflowerproject.model.entity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.myflowerproject.R;
import com.example.myflowerproject.model.adapter.OrderCartItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private Users user;
    private TextView txtNameUser;
    private TextView txtEmailUser;

    private RecyclerView orderCartRecyclerView;
    private OrderCartItemAdapter orderCartItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        orderCartRecyclerView = findViewById(R.id.order_cart_recyclerview);
        LinearLayoutManager layoutManagerCategory = new LinearLayoutManager(OrderActivity.this);
        layoutManagerCategory.setOrientation(LinearLayoutManager.HORIZONTAL); //list view ngang
        orderCartRecyclerView.setLayoutManager(layoutManagerCategory);

        List<OrderCartItemModel> orderCartItemModelList = new ArrayList<OrderCartItemModel>();
//        orderCartItemModelList.add(new OrderCartItemModel(R.mipmap.doc_do_bk32,1));
//        orderCartItemModelList.add(new OrderCartItemModel(R.mipmap.doc_hong_bk33,1));
//        orderCartItemModelList.add(new OrderCartItemModel(R.mipmap.doc_vang_bk4,1));
//        orderCartItemModelList.add(new OrderCartItemModel(R.mipmap.doc_do_bk32,1));
//        orderCartItemModelList.add(new OrderCartItemModel(R.mipmap.doc_hong_bk33,1));
//        orderCartItemModelList.add(new OrderCartItemModel(R.mipmap.doc_vang_bk4,1));

        orderCartItemAdapter = new OrderCartItemAdapter(orderCartItemModelList);
        orderCartRecyclerView.setAdapter(orderCartItemAdapter);
        orderCartItemAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

}
