package com.example.myflowerproject.model.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myflowerproject.model.entity.PreviewItemModel;
import com.example.myflowerproject.R;
import com.example.myflowerproject.view.ProductDetailActivity;

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
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view;
        if(convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.preview_item_layout,null);
            ImageView productImage = view.findViewById(R.id.product_image);
            TextView productName = view.findViewById(R.id.preview_item_name);
            TextView productPrice = view.findViewById(R.id.preview_item_price);

            productImage.setImageResource(previewItemModelList.get(position).getProductImage());
            productName.setText(previewItemModelList.get(position).getProductName());
            productPrice.setText(previewItemModelList.get(position).getProductPrice());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(parent.getContext(), ProductDetailActivity.class);
                    parent.getContext().startActivity(intent);
                }
            });
        }
        else {
            view = convertView;
        }
        return view;
    }
}
