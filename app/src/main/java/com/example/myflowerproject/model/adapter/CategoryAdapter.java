package com.example.myflowerproject.model.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myflowerproject.fragment.HomeFragment;
import com.example.myflowerproject.fragment.ListItemFragment;
import com.example.myflowerproject.model.entity.CategoryModel;
import com.example.myflowerproject.fragment.ListItem;
import com.example.myflowerproject.R;
import com.example.myflowerproject.view.HomeActivity;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<CategoryModel> categoryModelList;

    public CategoryAdapter(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    public CategoryAdapter(FragmentManager supportFragmentManager, int tabCount) {
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder viewHolder, int position) {
        int icon = categoryModelList.get(position).getCategoryIconLink();
        String name = categoryModelList.get(position).getCategoryName();
        int type = categoryModelList.get(position).getType();
        viewHolder.setCategoryItem(icon,name,type);
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    @NonNull
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                HomeFragment homeFragment = new HomeFragment(-1);
                return homeFragment;
            default:
                ListItemFragment listItemFragment = new ListItemFragment(i);
                return listItemFragment;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView categoryIcon;
        private TextView categoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIcon = itemView.findViewById(R.id.category_icon);
            categoryName = itemView.findViewById(R.id.category_name);
        }

        private void setCategoryItem(int resource, String name, int type){
            categoryIcon.setImageResource(resource);
            categoryName.setText(name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(type == 0){
                        Intent intent = new Intent(itemView.getContext(), HomeActivity.class);
                        itemView.getContext().startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(itemView.getContext(), ListItem.class);
                        intent.putExtra("type category", type);
                        itemView.getContext().startActivity(intent);
                    }
                }
            });
        }
    }
}
