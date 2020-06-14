package com.example.myflowerproject.ui.notification;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myflowerproject.Container;
import com.example.myflowerproject.R;
import com.example.myflowerproject.model.api.ApiUtils;
import com.example.myflowerproject.model.entity.Notification;
import com.example.myflowerproject.model.results.NotificationResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Notification extends Fragment {
    private ListView lvNotifi ;
    private List<Notification> notificationList;
    private NotificationAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment__notification, container, false);
        lvNotifi = view.findViewById(R.id.lvNotifi);
        notificationList = new ArrayList<>();
        lvNotifi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setBackgroundColor(Color.parseColor("#FFFFFF"));
                if (!notificationList.get(position).getCheck()){
                    seenNotification(notificationList.get(position).getId());
                    notificationList.get(position).setCheck(true);
                }
            }
        });

        getListNotification();
        return view;
    }

    public void getListNotification() {
        notificationList.clear();
        (ApiUtils.getNotificationAPI()).getNotification(Container.users.getToken()).enqueue(new Callback<NotificationResult>() {
            @Override
            public void onResponse(Call<NotificationResult> call, Response<NotificationResult> response) {
                if (response.isSuccessful()) {
                    notificationList = response.body().getList();
                    adapter = new NotificationAdapter(getContext(), R.layout.lines_notification, notificationList);
                    lvNotifi.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<NotificationResult> call, Throwable t) {

            }
        });
    }

    public void seenNotification(int id) {
        (ApiUtils.getNotificationAPI()).seenNotification(Container.users.getToken(), id).enqueue(new Callback<Notification>() {
            @Override
            public void onResponse(Call<Notification> call, Response<Notification> response) {

            }

            @Override
            public void onFailure(Call<Notification> call, Throwable t) {

            }
        });
    }
}
