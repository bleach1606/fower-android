package com.example.myflowerproject.ui.OrderDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myflowerproject.R;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.rvOrderCart);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<ProductDetailModel> arrayList = new ArrayList<>();
        arrayList.add(new ProductDetailModel(R.mipmap.doc_cam_bk3,"16"));
        arrayList.add(new ProductDetailModel(R.mipmap.doc_cam_bk3,"16"));
        arrayList.add(new ProductDetailModel(R.mipmap.doc_cam_bk3,"16"));
        arrayList.add(new ProductDetailModel(R.mipmap.doc_cam_bk3,"16"));
        arrayList.add(new ProductDetailModel(R.mipmap.doc_cam_bk3,"16"));

        ProductDetailAdapter productDetailAdapter = new ProductDetailAdapter(arrayList);

        recyclerView.setAdapter(productDetailAdapter);
        productDetailAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
