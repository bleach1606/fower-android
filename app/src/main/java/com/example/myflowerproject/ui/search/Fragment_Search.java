package com.example.myflowerproject.ui.search;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myflowerproject.Container;
import com.example.myflowerproject.R;
import com.example.myflowerproject.model.entity.FlowerProducts;
import com.example.myflowerproject.model.entity.Notification;
import com.example.myflowerproject.ui.notification.NotificationAdapter;
import com.example.myflowerproject.ui.notification.NotificationModel;
import com.example.myflowerproject.view.activity_productdetail.Activity_ProductDetail;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Search extends Fragment {

    private List<Notification> al;

    public Fragment_Search() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__search, container, false);
        al = new ArrayList<>();
        //todo some thinking
        for ( FlowerProducts sp: Container.flowerProductsList) {
            al.add(new Notification(sp.getName(),String.valueOf(sp.getPrice()), (sp.getAvatar())));
        }

//        al.add(new NotificationModel("Product Name","Color",R.mipmap.doc_hong_bk33));
//        al.add(new NotificationModel("Product Name","Color",R.mipmap.doc_hong_bk33));
//        al.add(new NotificationModel("Product Name","Color",R.mipmap.doc_hong_bk33));
//        al.add(new NotificationModel("Product Name","Color",R.mipmap.doc_hong_bk33));
        SearchAdapter adapter = new SearchAdapter(getContext(), R.layout.line_search_item, al);
        ListView listItemSearch = (ListView) view.findViewById(R.id.listItemSearch);
        listItemSearch.setAdapter(adapter);

        listItemSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                NotificationModel data = al.get(i);
                Intent intent = new Intent(getContext(), Activity_ProductDetail.class);
                intent.putExtra("id", Container.flowerProductsList.get(i).getId());
                getContext().startActivity(intent);
            }
        });
        return view;
    }
}
