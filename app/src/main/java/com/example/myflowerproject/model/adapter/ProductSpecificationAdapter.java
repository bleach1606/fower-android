package com.example.myflowerproject.model.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myflowerproject.R;
import com.example.myflowerproject.model.entity.ProductSpecificationModel;

import java.util.List;

public class ProductSpecificationAdapter extends RecyclerView.Adapter<ProductSpecificationAdapter.ViewHolder> {

    private List<ProductSpecificationModel> productSpecificationModelList;

    public ProductSpecificationAdapter(List<ProductSpecificationModel> productSpecificationModelList) {
        this.productSpecificationModelList = productSpecificationModelList;
    }

    @NonNull
    @Override
    public ProductSpecificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_specification_item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSpecificationAdapter.ViewHolder viewHolder, int position) {
        String featureName = productSpecificationModelList.get(position).getProductFeatureName();
        String featureValue = productSpecificationModelList.get(position).getProductFeatureValue();
        viewHolder.setProductFeatures(featureName,featureValue);
    }

    @Override
    public int getItemCount() {
        return productSpecificationModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView productFeatureName;
        private TextView productFeatureValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productFeatureName = itemView.findViewById(R.id.product_feature_name);
            productFeatureValue = itemView.findViewById(R.id.product_feature_value);
        }

        private void setProductFeatures(String featureName, String featureValue){
            productFeatureName.setText(featureName);
            productFeatureValue.setText(featureValue);
        }
    }
}
