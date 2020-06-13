package com.example.myflowerproject.ui.OrderDetail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myflowerproject.R;

import java.util.ArrayList;

public class OrderDetailFragment extends Fragment {

    public OrderDetailFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_order_detail, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rvOrderCart);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<ProductDetailModel> arrayList = new ArrayList<>();
        arrayList.add(new ProductDetailModel(R.mipmap.doc_cam_bk3,"16"));
        arrayList.add(new ProductDetailModel(R.mipmap.doc_cam_bk3,"16"));
        arrayList.add(new ProductDetailModel(R.mipmap.doc_cam_bk3,"16"));
        arrayList.add(new ProductDetailModel(R.mipmap.doc_cam_bk3,"16"));
        arrayList.add(new ProductDetailModel(R.mipmap.doc_cam_bk3,"16"));

        ProductDetailAdapter productDetailAdapter = new ProductDetailAdapter(arrayList);

        recyclerView.setAdapter(productDetailAdapter);
        productDetailAdapter.notifyDataSetChanged();


        return view;
    }

}
