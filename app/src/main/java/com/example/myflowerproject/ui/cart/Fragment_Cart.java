package com.example.myflowerproject.ui.cart;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myflowerproject.Container;
import com.example.myflowerproject.R;
import com.example.myflowerproject.model.entity.CartDetail;
import com.example.myflowerproject.view.activity_order.Activity_Order;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Cart extends Fragment {

    private RecyclerView cartItemRecyclerView;
    private Button btnOrder;

    public Fragment_Cart() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__cart, container, false);

        cartItemRecyclerView = view.findViewById(R.id.cart_item_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemRecyclerView.setLayoutManager(linearLayoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        if(Container.orderBill.getCartDetailList() != null)
            for(CartDetail cd: Container.orderBill.getCartDetailList()){
                cartItemModelList.add(new CartItemModel(0, cd));
            }
        cartItemModelList.add(new CartItemModel(1));

        CartItemAdapter cartItemAdapter = new CartItemAdapter(cartItemModelList);
        cartItemRecyclerView.setAdapter(cartItemAdapter);
        cartItemAdapter.notifyDataSetChanged();

        btnOrder = view.findViewById(R.id.order_btn);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_Order.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
