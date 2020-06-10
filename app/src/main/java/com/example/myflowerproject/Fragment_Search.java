package com.example.myflowerproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myflowerproject.model.adapter.NotificationAdapter;
import com.example.myflowerproject.model.entity.NotificationModel;
import com.example.myflowerproject.view.Notification;
import com.example.myflowerproject.view.activity_productdetail.Activity_ProductDetail;

import java.util.ArrayList;

public class Fragment_Search extends Fragment {

    private ArrayList<NotificationModel> al;

    public Fragment_Search() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__search, container, false);
        al = new ArrayList<NotificationModel>();
        al.add(new NotificationModel("Product Name","Color",R.mipmap.doc_hong_bk33));
        al.add(new NotificationModel("Product Name","Color",R.mipmap.doc_hong_bk33));
        al.add(new NotificationModel("Product Name","Color",R.mipmap.doc_hong_bk33));
        al.add(new NotificationModel("Product Name","Color",R.mipmap.doc_hong_bk33));
        NotificationAdapter adapter = new NotificationAdapter(getContext(), R.layout.lines_notification, al);
        ListView listItemSearch = (ListView) view.findViewById(R.id.listItemSearch);
        listItemSearch.setAdapter(adapter);

        listItemSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NotificationModel data = al.get(i);
                Intent intent = new Intent(getContext(), Activity_ProductDetail.class);
                intent.putExtra("id", 1);
                getContext().startActivity(intent);
            }
        });
        return view;
    }
}
