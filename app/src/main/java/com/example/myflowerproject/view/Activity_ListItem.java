package com.example.myflowerproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import com.example.myflowerproject.Container;
import com.example.myflowerproject.R;
import com.example.myflowerproject.ui.home.BouquetGridLayoutAdapter;
import com.example.myflowerproject.model.entity.CategoryModel;
import com.example.myflowerproject.model.entity.FlowerProducts;
import com.example.myflowerproject.model.entity.Users;

import java.util.ArrayList;
import java.util.List;

public class ListItemActivity extends AppCompatActivity {

    private Users user;
    private TextView txtNameUser;
    private TextView txtEmailUser;

    private GridView itemGridLayoutGridView;

    private List<FlowerProducts> listItem = new ArrayList<>();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int productType = getIntent().getIntExtra("product type",0);
        setListItem(productType);

        itemGridLayoutGridView = findViewById(R.id.item_grid_layout_grid_view);
        BouquetGridLayoutAdapter bouquetGridLayoutAdapter = new BouquetGridLayoutAdapter(listItem);
        itemGridLayoutGridView.setAdapter(bouquetGridLayoutAdapter);
        bouquetGridLayoutAdapter.notifyDataSetChanged();

    }

    public void setListItem(int type){
        for(CategoryModel cm: Container.listCategory){
            if(cm.getType()==type){
                toolbar.setTitle(cm.getCategoryName());
                listItem = cm.getFlowerProductsList();
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
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
