package com.example.myflowerproject.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myflowerproject.R;
import com.example.myflowerproject.view.HomeActivity;
import com.example.myflowerproject.view.MyAccount;
import com.example.myflowerproject.view.MyCartActivity;

public class NavigationFragment extends Fragment {

    private LinearLayout linearLayoutMyCart;
    private LinearLayout linearLayoutHome;
    private LinearLayout linearLayoutMyOrders;
    private LinearLayout linearLayoutMyAccount;
    private LinearLayout linearLayoutSignOut;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_navigation, container, false);

        linearLayoutMyCart = view.findViewById(R.id.nav_my_cart);
        linearLayoutHome = view.findViewById(R.id.nav_home);
        linearLayoutMyOrders = view.findViewById(R.id.nav_my_orders);
        linearLayoutMyAccount = view.findViewById(R.id.nav_my_account);
        linearLayoutSignOut = view.findViewById(R.id.nav_sign_out);

        linearLayoutMyCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cart_intent = new Intent(getContext(), MyCartActivity.class);
                startActivity(cart_intent);
            }
        });

        linearLayoutSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cart_intent = new Intent(getContext(), SignIn.class);
                startActivity(cart_intent);
            }
        });

        linearLayoutMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent cart_intent = new Intent(getContext(), MyAccount.class);
//                startActivity(cart_intent);
            }
        });

        linearLayoutMyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        linearLayoutHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cart_intent = new Intent(getContext(), HomeActivity.class);
                startActivity(cart_intent);
            }
        });

        return view;
    }
}