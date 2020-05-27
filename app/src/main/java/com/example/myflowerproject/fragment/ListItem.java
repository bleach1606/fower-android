package com.example.myflowerproject.fragment;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myflowerproject.R;
import com.example.myflowerproject.model.adapter.BouquetGridLayoutAdapter;
import com.example.myflowerproject.model.adapter.CategoryAdapter;
import com.example.myflowerproject.model.entity.CategoryModel;
import com.example.myflowerproject.model.entity.PreviewItemModel;

import java.util.ArrayList;
import java.util.List;

public class ListItem extends AppCompatActivity {

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;

    private TextView itemGridLayoutTitle;
    private GridView itemGridLayoutGridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_grid_layout);

        categoryRecyclerView = findViewById(R.id.category_recyclerview1);
        LinearLayoutManager layoutManagerCategory = new LinearLayoutManager(ListItem.this);
        layoutManagerCategory.setOrientation(LinearLayoutManager.HORIZONTAL); //list view ngang
        categoryRecyclerView.setLayoutManager(layoutManagerCategory);

        List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
        categoryModelList.add(new CategoryModel(R.mipmap.home_icon,"Home"));
        categoryModelList.add(new CategoryModel(R.mipmap.color_icon,"Color"));
        categoryModelList.add(new CategoryModel(R.mipmap.bo_hoa_icon,"Bouquet"));
        categoryModelList.add(new CategoryModel(R.mipmap.hop_hoa_icon,"Box"));
        categoryModelList.add(new CategoryModel(R.mipmap.ke_hoa_icon,"Shelf"));
        categoryModelList.add(new CategoryModel(R.mipmap.gio_hoa_icon,"Basket"));
        categoryModelList.add(new CategoryModel(R.mipmap.lo_hoa_icon,"Vase"));
        categoryModelList.add(new CategoryModel(R.mipmap.hoa_cuoi_icon,"Wedding"));

        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        ////////////////////////////

        itemGridLayoutTitle = findViewById(R.id.item_grid_layout_title);
        itemGridLayoutGridView = findViewById(R.id.item_grid_layout_grid_view);

        List<PreviewItemModel> listItem = new ArrayList<>();
        listItem.add(new PreviewItemModel(R.mipmap.vuong_do_bq15,"Bouqet Red","800.000 VND"));
        listItem.add(new PreviewItemModel(R.mipmap.vuong_do_bq13,"Bouqet Red","500.000 VND"));
        listItem.add(new PreviewItemModel(R.mipmap.vuong_hong_bq27,"Bouqet Pink","1.000.000 VND"));
        listItem.add(new PreviewItemModel(R.mipmap.vuong_hong_bq17,"Bouqet Pink","800.000 VND"));
        listItem.add(new PreviewItemModel(R.mipmap.vuong_do_bq15,"Bouqet Red","800.000 VND"));
        listItem.add(new PreviewItemModel(R.mipmap.vuong_do_bq13,"Bouqet Red","500.000 VND"));
        listItem.add(new PreviewItemModel(R.mipmap.vuong_hong_bq27,"Bouqet Pink","1.000.000 VND"));
        listItem.add(new PreviewItemModel(R.mipmap.vuong_hong_bq17,"Bouqet Pink","800.000 VND"));

        BouquetGridLayoutAdapter bouquetGridLayoutAdapter = new BouquetGridLayoutAdapter(listItem);
        itemGridLayoutGridView.setAdapter(bouquetGridLayoutAdapter);

        ////////////////////////////

    }
}