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

public class WaitAdapter extends RecyclerView.Adapter<WaitAdapter.ViewHolder> {
    ArrayList<WaitModel> waitModels;
    // Context context;

    public WaitAdapter(ArrayList<WaitModel> waitModels) {
        this.waitModels = waitModels;
        //this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.line_wait,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtsl.setText(waitModels.get(position).getQuantity());
        holder.txtPrice.setText(waitModels.get(position).getMoney());
        holder.imgWait.setImageResource(waitModels.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return waitModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtsl;
        TextView txtPrice;
        ImageView imgWait;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtsl = itemView.findViewById(R.id.txtsl);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            imgWait = itemView.findViewById(R.id.imgDelivery);
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
