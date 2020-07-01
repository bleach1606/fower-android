package com.example.myflowerproject.ui.home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myflowerproject.model.api.GetImage;
import com.example.myflowerproject.model.entity.FlowerProducts;
import com.example.myflowerproject.R;
import com.example.myflowerproject.view.activity_productdetail.Activity_ProductDetail;

import java.util.List;

public class BasketHorizontalScrollAdapter extends RecyclerView.Adapter<BasketHorizontalScrollAdapter.ViewHolder> {

    private List<FlowerProducts> flowerProductsList;


    public BasketHorizontalScrollAdapter(List<FlowerProducts> previewItemModelList) {
        this.flowerProductsList = previewItemModelList;
    }

    @NonNull
    @Override
    public BasketHorizontalScrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.preview_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        String resource = (flowerProductsList.get(position).getAvatar());
        String name = flowerProductsList.get(position).getName();
        String price = ""+(int)flowerProductsList.get(position).getPrice()+" VND";

        viewHolder.setProductImage(resource);
        viewHolder.setProductName(name);
        viewHolder.setProductPrice(price);
    }

    @Override
    public int getItemCount() {
        return Math.min(flowerProductsList.size(), 6);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productName;
        private TextView productPrice;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.preview_item_name);
            productPrice = itemView.findViewById(R.id.preview_item_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), Activity_ProductDetail.class);
                    intent.putExtra("id",  flowerProductsList.get(getAdapterPosition()).getId());
                    itemView.getContext().startActivity(intent);
                }
            });
        }

        private void setProductImage(String resource){
            new GetImage(productImage).execute(String.valueOf(resource));
        }

        private void setProductName(String name){
            productName.setText(name);
        }

        private void setProductPrice(String price){
            productPrice.setText(price);
        }

    }
}
