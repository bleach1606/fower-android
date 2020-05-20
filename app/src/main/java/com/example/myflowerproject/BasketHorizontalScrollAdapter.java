package com.example.myflowerproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BasketHorizontalScrollAdapter extends RecyclerView.Adapter<BasketHorizontalScrollAdapter.ViewHolder> {

    private List<PreviewItemModel> previewItemModelList;


    public BasketHorizontalScrollAdapter(List<PreviewItemModel> previewItemModelList) {
        this.previewItemModelList = previewItemModelList;
    }

    @NonNull
    @Override
    public BasketHorizontalScrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.preview_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        int resource = previewItemModelList.get(position).getProductImage();
        String name = previewItemModelList.get(position).getProductName();
        String price = previewItemModelList.get(position).getProductPrice();

        viewHolder.setProductImage(resource);
        viewHolder.setProductName(name);
        viewHolder.setProductPrice(price);
    }

    @Override
    public int getItemCount() {
        return previewItemModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productName;
        private TextView productPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.preview_item_image);
            productName = itemView.findViewById(R.id.preview_item_name);
            productPrice = itemView.findViewById(R.id.preview_item_price);
        }

        private void setProductImage(int resource){
            productImage.setImageResource(resource);
        }

        private void setProductName(String name){
            productName.setText(name);
        }

        private void setProductPrice(String price){
            productPrice.setText(price);
        }

    }
}
