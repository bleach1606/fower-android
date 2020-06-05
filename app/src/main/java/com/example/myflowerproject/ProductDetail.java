package com.example.myflowerproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myflowerproject.model.adapter.ProductDetailAdapter;
import com.example.myflowerproject.model.adapter.WaitAdapter;
import com.example.myflowerproject.model.entity.ProductDetailModel;
import com.example.myflowerproject.model.entity.WaitModel;

import java.util.ArrayList;

public class ProductDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail2);

        Toolbar toolbar = findViewById(R.id.toolbarProduct);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        init1();

    }
    public void init1(){
        RecyclerView recyclerView = findViewById(R.id.rvOrderCart);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<ProductDetailModel> arrayList = new ArrayList<>();
        arrayList.add(new ProductDetailModel(R.mipmap.vuong_do_bq15,"16"));
        arrayList.add(new ProductDetailModel(R.mipmap.vuong_do_bq15,"2"));
        arrayList.add(new ProductDetailModel(R.mipmap.vuong_do_bq15,"1"));
        arrayList.add(new ProductDetailModel(R.mipmap.vuong_do_bq15,"2"));
        arrayList.add(new ProductDetailModel(R.mipmap.vuong_do_bq15,"1"));

        ProductDetailAdapter productDetailAdapter = new ProductDetailAdapter(arrayList);

        recyclerView.setAdapter(productDetailAdapter);
        productDetailAdapter.notifyDataSetChanged();
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
