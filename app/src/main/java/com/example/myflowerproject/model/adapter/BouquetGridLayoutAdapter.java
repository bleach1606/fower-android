package com.example.myflowerproject.model.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myflowerproject.model.entity.PreviewItemModel;
import com.example.myflowerproject.R;

import java.util.List;

public class BouquetGridLayoutAdapter extends BaseAdapter {

    List<PreviewItemModel> previewItemModelList;

    public BouquetGridLayoutAdapter(List<PreviewItemModel> previewItemModelList) {
        this.previewItemModelList = previewItemModelList;
    }

    @Override
    public int getCount() {
        return previewItemModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.preview_item_layout,null);
            ImageView productImage = view.findViewById(R.id.product_image);
            TextView productName = view.findViewById(R.id.preview_item_name);
            TextView productPrice = view.findViewById(R.id.preview_item_price);

            productImage.setImageResource(previewItemModelList.get(position).getProductImage());
            productName.setText(previewItemModelList.get(position).getProductName());
            productPrice.setText(previewItemModelList.get(position).getProductPrice());
        }
        else {
            view = convertView;
        }
        return view;
    }
}
