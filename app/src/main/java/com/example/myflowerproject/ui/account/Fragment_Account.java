package com.example.myflowerproject.ui.account;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myflowerproject.Container;
import com.example.myflowerproject.R;
import com.example.myflowerproject.model.api.GetImage;

public class Fragment_Account extends Fragment {
    EditText etFirstName,etLastName,etEmail,etPhone;
    Button btnSave;
    ImageView imgAvt,imgCreate;


    public Fragment_Account() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__account, container, false);

        etFirstName = view.findViewById(R.id.etFirstName);
        etLastName = view.findViewById(R.id.etLastName);
        etEmail = view.findViewById(R.id.etEmail);
        etPhone = view.findViewById(R.id.etPhone);
        btnSave = view.findViewById(R.id.btnSave);
        imgAvt = view.findViewById(R.id.imgAvt);
        imgCreate = view.findViewById(R.id.imgCreate);
        new GetImage(imgAvt).execute(Container.users.getPeople().getAvatar());
        etLastName.setText(Container.users.getPeople().getLastName());
        etFirstName.setText(Container.users.getPeople().getFirstName());
        etEmail.setText(Container.users.getPeople().getEmail());
        etPhone.setText(Container.users.getPeople().getPhoneNumber());
        return view;
    }
}
