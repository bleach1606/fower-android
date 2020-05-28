package com.example.myflowerproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myflowerproject.R;
import com.example.myflowerproject.model.adapter.CartItemAdapter;
import com.example.myflowerproject.model.entity.CartItemModel;
import com.example.myflowerproject.model.entity.OrderActivity;
import com.example.myflowerproject.model.entity.Users;

import java.util.ArrayList;
import java.util.List;

public class MyCartActivity extends AppCompatActivity {
    private Users user;
    private TextView txtNameUser;
    private TextView txtEmailUser;

    private ImageView productImageView;

    private RecyclerView cartItemRecyclerView;

    private Button btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cartItemRecyclerView = findViewById(R.id.cart_item_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyCartActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemRecyclerView.setLayoutManager(linearLayoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0,R.mipmap.doc_do_bk32,"Product Name",2000000,2200000,1));
        cartItemModelList.add(new CartItemModel(0,R.mipmap.doc_hong_bk33,"Product Name",2000000,2200000,1));
        cartItemModelList.add(new CartItemModel(0,R.mipmap.doc_vang_bk4,"Product Name",2000000,2200000,1));
        cartItemModelList.add(new CartItemModel(1));

        CartItemAdapter cartItemAdapter = new CartItemAdapter(cartItemModelList);
        cartItemRecyclerView.setAdapter(cartItemAdapter);
        cartItemAdapter.notifyDataSetChanged();

        btnOrder = findViewById(R.id.order_btn);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCartActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        txtNameUser = findViewById(R.id.nav_header_home_fullname);
        txtEmailUser = findViewById(R.id.nav_header_home_email);
        Intent intent = getIntent();
        user = (Users) intent.getSerializableExtra("user");
//        Toast.makeText( getBaseContext(), user.toString(), Toast.LENGTH_SHORT).show();

//        txtNameUser.setText(user.getPeople().getName());
//        txtEmailUser.setText(user.getUsername());
        return true;
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
