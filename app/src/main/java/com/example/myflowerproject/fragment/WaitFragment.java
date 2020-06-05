package com.example.myflowerproject.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myflowerproject.R;
import com.example.myflowerproject.model.adapter.WaitAdapter;
import com.example.myflowerproject.model.entity.WaitModel;

import java.util.ArrayList;

import static androidx.recyclerview.widget.LinearLayoutManager.*;


public class WaitFragment extends Fragment {


    public WaitFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_wait, container, false);


            RecyclerView recyclerView = view.findViewById(R.id.rvWait);
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
            testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(testingLayoutManager);
            ArrayList<WaitModel> arrayList = new ArrayList<>();
            arrayList.add(new WaitModel(R.mipmap.vuong_hong_bq17,"16","10.000.000"));
            arrayList.add(new WaitModel(R.mipmap.vuong_hong_bq17,"16","10.000.000"));
            arrayList.add(new WaitModel(R.mipmap.vuong_hong_bq17,"16","10.000.000"));
            arrayList.add(new WaitModel(R.mipmap.vuong_hong_bq17,"16","10.000.000"));
            arrayList.add(new WaitModel(R.mipmap.vuong_hong_bq17,"16","10.000.000"));

            WaitAdapter waitAdapter = new WaitAdapter(arrayList);

            recyclerView.setAdapter(waitAdapter);
            waitAdapter.notifyDataSetChanged();

        return view;
    }

}
