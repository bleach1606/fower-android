package com.example.myflowerproject.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myflowerproject.R;
import com.example.myflowerproject.model.entity.ProductDetailModel;

import java.util.ArrayList;

public class ProductDetailAdapter extends RecyclerView.Adapter <ProductDetailAdapter.ViewHolder>{
    ArrayList<ProductDetailModel> productDetailModels;
    //Context context;

    public ProductDetailAdapter(ArrayList<ProductDetailModel> productDetailModels) {
        this.productDetailModels = productDetailModels;
        //this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.order_cart_item,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.order_cart_item_image.setImageResource(productDetailModels.get(position).getImg());
        holder.order_cart_item_quantity.setText(productDetailModels.get(position).getSl());
    }

    @Override
    public int getItemCount() {
        return productDetailModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView order_cart_item_quantity;
        ImageView order_cart_item_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            order_cart_item_quantity = itemView.findViewById(R.id.order_cart_item_quantity);
            order_cart_item_image = itemView.findViewById(R.id.order_cart_item_image);

        }
    }
}
