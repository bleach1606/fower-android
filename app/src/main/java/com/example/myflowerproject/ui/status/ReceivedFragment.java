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

public class ReceivedFragment extends Fragment {

    public ReceivedFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_received, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rvReceived);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(testingLayoutManager);
        ArrayList<ReceivedModel> arrayList = new ArrayList<>();
        arrayList.add(new ReceivedModel(R.mipmap.doc_hong_bk33,"16","10.000.000"));
        arrayList.add(new ReceivedModel(R.mipmap.doc_hong_bk33,"16","10.000.000"));
        arrayList.add(new ReceivedModel(R.mipmap.doc_hong_bk33,"16","10.000.000"));
        arrayList.add(new ReceivedModel(R.mipmap.doc_hong_bk33,"16","10.000.000"));
        arrayList.add(new ReceivedModel(R.mipmap.doc_hong_bk33,"16","10.000.000"));
        arrayList.add(new ReceivedModel(R.mipmap.doc_hong_bk33,"16","10.000.000"));

        ReceivedAdapter waitAdapter = new ReceivedAdapter(arrayList);

        recyclerView.setAdapter(waitAdapter);
        waitAdapter.notifyDataSetChanged();
        return view;
    }
}
