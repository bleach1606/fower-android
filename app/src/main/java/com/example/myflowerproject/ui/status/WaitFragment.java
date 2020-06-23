package com.example.myflowerproject.ui.status;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myflowerproject.Container;
import com.example.myflowerproject.R;
import com.example.myflowerproject.model.api.ApiUtils;
import com.example.myflowerproject.model.entity.CartDetail;
import com.example.myflowerproject.model.entity.OrderBill;
import com.example.myflowerproject.model.results.ListOrderBillResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WaitFragment extends Fragment {

    private ArrayList<WaitModel> arrayList;
    private RecyclerView recyclerView;
    private WaitAdapter waitAdapter;

    public WaitFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_wait, container, false);

        recyclerView = view.findViewById(R.id.rvWait);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(testingLayoutManager);
        arrayList = new ArrayList<>();

        getListOrderBills();

        waitAdapter = new WaitAdapter(arrayList);

        recyclerView.setAdapter(waitAdapter);
        waitAdapter.notifyDataSetChanged();

        return view;
    }

    public void getListOrderBills() {
        try {

            (ApiUtils.getOrderBillAPI()).getOrderList(Container.users.getToken()).enqueue(new Callback<ListOrderBillResult>() {
                @Override
                public void onResponse(Call<ListOrderBillResult> call, Response<ListOrderBillResult> response) {
                    if (response.isSuccessful()) {
                        for (OrderBill ob : response.body().getOrderBillList() ) {
                            //todo if else
                            if (ob.getStatus() != 2) continue;
                            long sum = 0;
                            for ( CartDetail x : ob.getCartDetailList()) {
                                sum += x.getFlowerProduct().getPrice() * x.getNumber();
                            }
                            Integer k = 1;
                            if (ob.getCartDetailList().size() > 0 && Integer.valueOf(ob.getCartDetailList().get(0).getFlowerProduct().getAvatar()) != null ){
                                k  = Integer.valueOf(ob.getCartDetailList().get(0).getFlowerProduct().getAvatar());
                            }
                            WaitModel waitModel = new WaitModel(
                                    k,
                                    String.valueOf(ob.getCartDetailList().size()), String.valueOf(sum));
                            arrayList.add(waitModel);
                        }

                        waitAdapter = new WaitAdapter(arrayList);

                        recyclerView.setAdapter(waitAdapter);
                        waitAdapter.notifyDataSetChanged();
                    }

                }

                @Override
                public void onFailure(Call<ListOrderBillResult> call, Throwable t) {

                }
            });
        } catch (Exception ex) {
            throw ex;
        }
    }
}
