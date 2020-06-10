package com.example.myflowerproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import com.example.myflowerproject.Container;
import com.example.myflowerproject.R;
import com.example.myflowerproject.ui.home.BouquetGridLayoutAdapter;
import com.example.myflowerproject.model.entity.Category;
import com.example.myflowerproject.model.entity.FlowerProducts;
import com.example.myflowerproject.model.entity.Users;

import java.util.ArrayList;
import java.util.List;

public class Activity_ListItem extends AppCompatActivity {

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int productType = getIntent().getIntExtra("product type",0);
        setListItem(productType);

        itemGridLayoutGridView = findViewById(R.id.item_grid_layout_grid_view);
        BouquetGridLayoutAdapter bouquetGridLayoutAdapter = new BouquetGridLayoutAdapter(listItem);
        itemGridLayoutGridView.setAdapter(bouquetGridLayoutAdapter);
        bouquetGridLayoutAdapter.notifyDataSetChanged();

    }

    public void setListItem(int type){
        for(Category cm: Container.listCategory){
            if(cm.getType()==type){
                toolbar.setTitle(cm.getCategoryName());
                listItem = cm.getFlowerProductsList();
                break;
            }
        }
    }
}
