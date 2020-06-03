package com.example.myflowerproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myflowerproject.R;
import com.example.myflowerproject.model.adapter.BouquetGridLayoutAdapter;
import com.example.myflowerproject.model.entity.PreviewItemModel;

import java.util.ArrayList;
import java.util.List;

public class ListItemFragment extends Fragment {

    private int productType;

    public ListItemFragment(int productType) {
        this.productType = productType;
    }

    private GridView itemGridLayoutGridView;

    private List<PreviewItemModel> listItem = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.list_item_grid_layout, container, false);

        setListItem(productType);

        itemGridLayoutGridView = view.findViewById(R.id.item_grid_layout_grid_view);
        BouquetGridLayoutAdapter bouquetGridLayoutAdapter = new BouquetGridLayoutAdapter(listItem);
        itemGridLayoutGridView.setAdapter(bouquetGridLayoutAdapter);
        bouquetGridLayoutAdapter.notifyDataSetChanged();

        return view;
    }

    public void setListItem(int type){
//        listItem.add(new PreviewItemModel(R.mipmap.vuong_do_bq15,"Bouqet Red","800.000 VND"));
//        listItem.add(new PreviewItemModel(R.mipmap.vuong_do_bq13,"Bouqet Red","500.000 VND"));
//        listItem.add(new PreviewItemModel(R.mipmap.vuong_hong_bq27,"Bouqet Pink","1.000.000 VND"));
//        listItem.add(new PreviewItemModel(R.mipmap.vuong_hong_bq17,"Bouqet Pink","800.000 VND"));
//        listItem.add(new PreviewItemModel(R.mipmap.vuong_do_bq15,"Bouqet Red","800.000 VND"));
//        listItem.add(new PreviewItemModel(R.mipmap.vuong_do_bq13,"Bouqet Red","500.000 VND"));
//        listItem.add(new PreviewItemModel(R.mipmap.vuong_hong_bq27,"Bouqet Pink","1.000.000 VND"));
//        listItem.add(new PreviewItemModel(R.mipmap.vuong_hong_bq17,"Bouqet Pink","800.000 VND"));
    }

}
