package com.example.myflowerproject.ui.home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myflowerproject.model.api.GetImage;
import com.example.myflowerproject.model.entity.FlowerProducts;
import com.example.myflowerproject.R;
import com.example.myflowerproject.view.activity_productdetail.Activity_ProductDetail;

import java.util.List;

public class BouquetGridLayoutAdapter extends BaseAdapter {

    List<FlowerProducts> flowerProductsList;

    public BouquetGridLayoutAdapter(List<FlowerProducts> previewItemModelList) {
        this.flowerProductsList = previewItemModelList;
    }

    @Override
    public int getCount() {
        return flowerProductsList.size();
    }

    @Override
    public Object getItem(int position) {
        return flowerProductsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return flowerProductsList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view;
        if(convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.preview_item_layout,null);
            ImageView productImage = view.findViewById(R.id.product_image);
            TextView productName = view.findViewById(R.id.preview_item_name);
            TextView productPrice = view.findViewById(R.id.preview_item_price);

            new GetImage(productImage).execute(""+flowerProductsList.get(position).getAvatar());
            productName.setText(flowerProductsList.get(position).getName());
            productPrice.setText(""+flowerProductsList.get(position).getPrice());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(parent.getContext(), Activity_ProductDetail.class);
                    intent.putExtra("id",  flowerProductsList.get(position).getId());
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
