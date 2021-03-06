package com.example.myflowerproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myflowerproject.R;
import com.example.myflowerproject.model.adapter.BouquetGridLayoutAdapter;
import com.example.myflowerproject.model.adapter.CategoryAdapter;
import com.example.myflowerproject.model.entity.PreviewItemModel;
import com.example.myflowerproject.model.entity.Users;
import com.example.myflowerproject.view.MyCartActivity;

import java.util.ArrayList;
import java.util.List;

public class ListItem extends AppCompatActivity {
    private Users user;
    private TextView txtNameUser;
    private TextView txtEmailUser;

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;

    private Toolbar toolbar;

    private GridView itemGridLayoutGridView;

    private List<PreviewItemModel> listItem = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_grid_layout);
//
//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        categoryRecyclerView = findViewById(R.id.category_recyclerview1);
//        LinearLayoutManager layoutManagerCategory = new LinearLayoutManager(ListItem.this);
//        layoutManagerCategory.setOrientation(LinearLayoutManager.HORIZONTAL); //list view ngang
//        categoryRecyclerView.setLayoutManager(layoutManagerCategory);
//
//        List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
//        categoryModelList.add(new CategoryModel(R.mipmap.home_icon,"Home",0));
//        categoryModelList.add(new CategoryModel(R.mipmap.bo_hoa_icon,"Bouquet",1));
//        categoryModelList.add(new CategoryModel(R.mipmap.hop_hoa_icon,"Box",2));
//        categoryModelList.add(new CategoryModel(R.mipmap.ke_hoa_icon,"Shelf",3));
//        categoryModelList.add(new CategoryModel(R.mipmap.gio_hoa_icon,"Basket",4));
//        categoryModelList.add(new CategoryModel(R.mipmap.lo_hoa_icon,"Vase",5));
//        categoryModelList.add(new CategoryModel(R.mipmap.hoa_cuoi_icon,"Wedding",6));
//
//        categoryAdapter = new CategoryAdapter(categoryModelList);
//        categoryRecyclerView.setAdapter(categoryAdapter);
//        categoryAdapter.notifyDataSetChanged();

        ////////////////////////////

        Intent intent = getIntent();
        int type = intent.getIntExtra("type category",0);
        switch (type){
            case 1:
                toolbar.setTitle("Flower Bouquet");
                setListItem(type);
                break;
            case 2:
                toolbar.setTitle("Flower Box");
                setListItem(type);
                break;
            case 3:
                toolbar.setTitle("Flower Shelf");
                setListItem(type);
                break;
            case 4:
                toolbar.setTitle("Flower Basket");
                setListItem(type);
                break;
            case 5:
                toolbar.setTitle("Flower Vase");
                setListItem(type);
                break;
            case 6:
                toolbar.setTitle("Wedding");
                setListItem(type);
                break;
            default:

        }

        itemGridLayoutGridView = findViewById(R.id.item_grid_layout_grid_view);
        BouquetGridLayoutAdapter bouquetGridLayoutAdapter = new BouquetGridLayoutAdapter(listItem);
        itemGridLayoutGridView.setAdapter(bouquetGridLayoutAdapter);

        ////////////////////////////

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
                Intent intent = new Intent(ListItem.this, MyCartActivity.class);
                startActivity(intent);
                return true;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

}
