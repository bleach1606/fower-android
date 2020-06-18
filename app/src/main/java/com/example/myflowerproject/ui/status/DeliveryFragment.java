package com.example.myflowerproject.ui.status;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myflowerproject.R;

import java.util.ArrayList;

public class DeliveryFragment extends Fragment {

    public DeliveryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_delivery, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rvDelivery);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(testingLayoutManager);
        ArrayList<DeliveryModel> arrayList = new ArrayList<>();
        arrayList.add(new DeliveryModel(R.mipmap.doc_vang_bk4,"16","10.000.000"));
        arrayList.add(new DeliveryModel(R.mipmap.doc_vang_bk4,"16","10.000.000"));
        arrayList.add(new DeliveryModel(R.mipmap.doc_vang_bk4,"16","10.000.000"));
        arrayList.add(new DeliveryModel(R.mipmap.doc_vang_bk4,"16","10.000.000"));
        arrayList.add(new DeliveryModel(R.mipmap.doc_vang_bk4,"16","10.000.000"));


        DeliveryAdapter waitAdapter = new DeliveryAdapter(arrayList);

        recyclerView.setAdapter(waitAdapter);
        waitAdapter.notifyDataSetChanged();
        return view;
    }
}
