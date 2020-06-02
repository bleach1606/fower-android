package com.example.myflowerproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import com.example.myflowerproject.R;
import com.example.myflowerproject.model.adapter.BouquetGridLayoutAdapter;
import com.example.myflowerproject.model.entity.PreviewItemModel;
import com.example.myflowerproject.model.entity.Users;

import java.util.ArrayList;
import java.util.List;

public class ListItemActivity extends AppCompatActivity {

    private Users user;
    private TextView txtNameUser;
    private TextView txtEmailUser;

    private GridView itemGridLayoutGridView;

    private List<PreviewItemModel> listItem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int productType = getIntent().getIntExtra("product type",0);
        setListItem(productType);

        toolbar.setTitle("ABC");
        itemGridLayoutGridView = findViewById(R.id.item_grid_layout_grid_view);
        BouquetGridLayoutAdapter bouquetGridLayoutAdapter = new BouquetGridLayoutAdapter(listItem);
        itemGridLayoutGridView.setAdapter(bouquetGridLayoutAdapter);
        bouquetGridLayoutAdapter.notifyDataSetChanged();

    }

    public void setListItem(int type){
        listItem.add(new PreviewItemModel(R.mipmap.vuong_do_bq15,"Bouqet Red","800.000 VND"));
        listItem.add(new PreviewItemModel(R.mipmap.vuong_do_bq13,"Bouqet Red","500.000 VND"));
        listItem.add(new PreviewItemModel(R.mipmap.vuong_hong_bq27,"Bouqet Pink","1.000.000 VND"));
        listItem.add(new PreviewItemModel(R.mipmap.vuong_hong_bq17,"Bouqet Pink","800.000 VND"));
        listItem.add(new PreviewItemModel(R.mipmap.vuong_do_bq15,"Bouqet Red","800.000 VND"));
        listItem.add(new PreviewItemModel(R.mipmap.vuong_do_bq13,"Bouqet Red","500.000 VND"));
        listItem.add(new PreviewItemModel(R.mipmap.vuong_hong_bq27,"Bouqet Pink","1.000.000 VND"));
        listItem.add(new PreviewItemModel(R.mipmap.vuong_hong_bq17,"Bouqet Pink","800.000 VND"));
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
