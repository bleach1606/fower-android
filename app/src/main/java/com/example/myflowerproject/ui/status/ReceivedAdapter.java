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

import java.util.ArrayList;

public class ReceivedAdapter  extends RecyclerView.Adapter<ReceivedAdapter.ViewHolder>{
    ArrayList<ReceivedModel> receivedModels;

    public ReceivedAdapter(ArrayList<ReceivedModel> receivedModels) {
        this.receivedModels = receivedModels;
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
        holder.imgDelivery.setImageResource(receivedModels.get(position).getImg());
        holder.txtsl.setText(receivedModels.get(position).getQuantity());
        holder.txtPrice.setText(receivedModels.get(position).getMoney());
    }

    @Override
    public int getItemCount() {
        return receivedModels.size();
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
                    Intent intent = new Intent(itemView.getContext(), MyOrderFragment.class);
                    //intent.putExtra("id", );
                    itemView.getContext().startActivity(intent);
                }
            });

        }
    }
}
