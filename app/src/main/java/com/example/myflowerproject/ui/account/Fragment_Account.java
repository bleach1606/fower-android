package com.example.myflowerproject.ui.account;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myflowerproject.Container;
import com.example.myflowerproject.R;
import com.example.myflowerproject.model.api.ApiUtils;
import com.example.myflowerproject.model.api.GetImage;
import com.example.myflowerproject.model.api.UserAPI;
import com.example.myflowerproject.model.entity.People;
import com.example.myflowerproject.model.entity.Users;
import com.example.myflowerproject.model.results.UploadResult;
import com.example.myflowerproject.model.results.UserResult;
import com.facebook.AccessToken;
import com.squareup.picasso.Picasso;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class Fragment_Account extends Fragment {
    EditText etFirstName,etLastName,etEmail,etPhone;
    Button btnSave;
    ImageView imgAvt,imgCreate;
    private UserAPI userAPI;
    Uri uriImage;
    String imgPath;
    public Fragment_Account() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__account, container, false);

        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);

            }

        }

        etFirstName = view.findViewById(R.id.etFirstName);
        etLastName = view.findViewById(R.id.etLastName);
        etEmail = view.findViewById(R.id.etEmail);
        etPhone = view.findViewById(R.id.etPhone);
        btnSave = view.findViewById(R.id.btnSave);
        imgAvt = view.findViewById(R.id.imgAvt);
        imgCreate = view.findViewById(R.id.imgCreate);
        if(Container.users.getType()==2) {
            String uri = "https://graph.facebook.com/" + AccessToken.getCurrentAccessToken().getUserId() + "/picture";
            Picasso.get().load(uri).resize(100, 100).centerCrop().into((CircleImageView) view.findViewById(R.id.imgAvt));
        }else{
            new GetImage(imgAvt).execute(Container.users.getPeople().getAvatar());
        }
        etLastName.setText(Container.users.getPeople().getLastName());
        etFirstName.setText(Container.users.getPeople().getFirstName());
        etEmail.setText(Container.users.getPeople().getEmail());
        etPhone.setText(Container.users.getPeople().getPhoneNumber());

        userAPI = ApiUtils.getAPIService();
        etFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Users user = Container.users;
                    user.setFiActive(true);
                    People people = user.getPeople();
                    people.setFirstName(etFirstName.getText().toString());
                    people.setLastName(etLastName.getText().toString());
                    people.setPhoneNumber(etPhone.getText().toString());
                    people.setEmail(etEmail.getText().toString());
                    people.setActive(true);
                    user.setPeople(people);
                    user.getPeople().setBirthday(null);
                    // doan call API upload images ở đây


                    userAPI.updateUser(user.getToken(), user).enqueue(new Callback<UserResult>() {
                        @Override
                        public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getContext(), "Lưu Thành Công", Toast.LENGTH_SHORT).show();
                                Container.users = user;
                            } else {
                                Toast.makeText(getContext(), "Lưu fail", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserResult> call, Throwable t) {
                            System.out.println();
                        }
                    });
                }
                catch(Exception ex){
                    Toast.makeText(getContext(), ex.toString(), Toast.LENGTH_SHORT).show();
                    System.out.println(ex.toString());
                }
            }
        });
        imgCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK) {
            uriImage = data.getData();
            Picasso.get().load(data.getDataString()).resize(100, 100).centerCrop().into(imgAvt);
            Uri selectedImage = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor c = getContext().getContentResolver().query(selectedImage,filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            imgPath = picturePath;
            c.close();
            if(uriImage != null){
                System.out.println(uriImage.toString());
                File file = new File(imgPath);

                Log.i("size", ""+file.getAbsolutePath() + "\n" + imgPath);
                RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);

                MultipartBody.Part part = MultipartBody.Part.createFormData("file", null , fileReqBody);

                RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);


                ApiUtils.getUploadAPI().postImage(fileToUpload).enqueue(new Callback<UploadResult>() {
                    @Override
                    public void onResponse(Call<UploadResult> call, Response<UploadResult> response) {
                        System.out.println(response.body().getUrl());
                        Container.users.getPeople().setAvatar(response.body().getUrl());
                    }

                    @Override
                    public void onFailure(Call<UploadResult> call, Throwable t) {
                        System.out.println("no hope");
                    }
                });
            }

        }
    }

    private void checkInputs(){
        if(!TextUtils.isEmpty(etFirstName.getText())){
            if(!TextUtils.isEmpty(etLastName.getText())){
                if(!TextUtils.isEmpty(etEmail.getText())){
                    if(!TextUtils.isEmpty(etPhone.getText())){
                        btnSave.setEnabled(true);
                        btnSave.setTextColor(Color.rgb(255,255,255));
                    }
                    else {
                        btnSave.setEnabled(false);
                        btnSave.setTextColor(Color.rgb(238,180,180));
                    }
                }
                else {
                    btnSave.setEnabled(false);
                    btnSave.setTextColor(Color.rgb(238,180,180));
                }
            }
            else {
                btnSave.setEnabled(false);
                btnSave.setTextColor(Color.rgb(238,180,180));
            }
        }
        else {
            btnSave.setEnabled(false);
            btnSave.setTextColor(Color.rgb(238, 180, 180));
        }
    }
}
