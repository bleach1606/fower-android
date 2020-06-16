package com.example.myflowerproject.ui.status;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myflowerproject.R;
import com.example.myflowerproject.ui.OrderDetail.OrderDetailActivity;

import java.util.ArrayList;

public class DeliveryAdapter  extends RecyclerView.Adapter<DeliveryAdapter.ViewHolder> {
    ArrayList<DeliveryModel> deliveryModels;

    public DeliveryAdapter(ArrayList<DeliveryModel> deliveryModels) {
        this.deliveryModels = deliveryModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.line_delivery,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgDelivery.setImageResource(deliveryModels.get(position).getImg());
        holder.txtsl.setText(deliveryModels.get(position).getQuantity());
        holder.txtPrice.setText(deliveryModels.get(position).getMoney());

    }

    @Override
    public int getItemCount() {
        return deliveryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtsl;
        TextView txtPrice;
        ImageView imgDelivery;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtsl = itemView.findViewById(R.id.txtsl);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            imgDelivery = itemView.findViewById(R.id.imgDelivery);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), OrderDetailActivity.class);
                    //intent.putExtra("id", );
                    itemView.getContext().startActivity(intent);
                }
            });

        }
    }
}

