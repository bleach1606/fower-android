package com.example.myflowerproject.view.activity_order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myflowerproject.R;

import java.util.List;

public class OrderCartItemAdapter extends RecyclerView.Adapter<OrderCartItemAdapter.ViewHolder> {

    private List<OrderCartItemModel> orderCartItemModelList;

    public OrderCartItemAdapter(List<OrderCartItemModel> orderCartItemModelList) {
        this.orderCartItemModelList = orderCartItemModelList;
    }

    @NonNull
    @Override
    public OrderCartItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_cart_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderCartItemAdapter.ViewHolder viewHolder, int position) {
        int image = orderCartItemModelList.get(position).getItemImage();
        int quantity = orderCartItemModelList.get(position).getItemQuantity();
        viewHolder.setItemImage(image);
        viewHolder.setItemQuantity(quantity);
    }

    @Override
    public int getItemCount() {
        return orderCartItemModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView orderCartItemImage;
        private TextView orderCartItemQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderCartItemImage = itemView.findViewById(R.id.order_cart_item_image);
            orderCartItemQuantity = itemView.findViewById(R.id.order_cart_item_quantity);
        }

        private void setItemImage(int resource){
            orderCartItemImage.setImageResource(resource);
        }

        private void setItemQuantity(int quantity){
            String str = "" + quantity;
            orderCartItemQuantity.setText(str);
        }
    }
}
