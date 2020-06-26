package com.example.myflowerproject.ui.account;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.FileUtils;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

//import com.bumptech.glide.Glide;
import com.example.myflowerproject.Container;
import com.example.myflowerproject.R;
import com.example.myflowerproject.model.api.ApiUtils;
import com.example.myflowerproject.model.api.GetImage;
import com.example.myflowerproject.model.api.UploadImageAPI;
import com.example.myflowerproject.model.api.UserAPI;
import com.example.myflowerproject.model.entity.People;
import com.example.myflowerproject.model.entity.Users;
import com.example.myflowerproject.model.results.UserLoginResult;
import com.example.myflowerproject.model.results.UserResult;
import com.example.myflowerproject.view.Activity_SignIn;
import com.facebook.AccessToken;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;

public class Fragment_Account extends Fragment {
    EditText etFirstName,etLastName,etEmail,etPhone;
    Button btnSave;
    ImageView imgAvt,imgCreate;
    private UserAPI userAPI;
    Uri uriImage;
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
                    if(uriImage != null){
//                        String filePath = getRealPathFromURIPath(uri, MainActivity.this);
//                        File file = new File(uriImage.toString());
//                        RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
//                        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
//                        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
//
//                        ApiUtils.getUploadAPI().postImage(fileToUpload).enqueue(new Callback<String>() {
//                            @Override
//                            public void onResponse(Call<String> call, Response<String> response) {
//                                System.out.println(response.body());
//                            }
//
//                            @Override
//                            public void onFailure(Call<String> call, Throwable t) {
//
//                            }
//                        });
                        OkHttpClient client = new OkHttpClient().newBuilder()
                                .build();
                        MediaType mediaType = MediaType.parse("text/plain");
                        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                                .addFormDataPart("file","a.png",
                                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                                new File(uriImage.toString())))
                                .build();
                        Request request = new Request.Builder()
                                .url("http://192.168.43.209:8080/public/upload")
                                .method("POST", body)
                                .addHeader("Cookie", "JSESSIONID=78AE6CA3034045B9DA2ACDCCFFE77EB0")
                                .build();
                        Response response = client.newCall(request).execute();

                    }

//                    userAPI.updateUser(user.getToken(), user).enqueue(new Callback<UserResult>() {
//                        @Override
//                        public void onResponse(Call<UserResult> call, Response<UserResult> response) {
//                            if (response.isSuccessful()) {
//                                Toast.makeText(getContext(), "Lưu Thành Công", Toast.LENGTH_SHORT).show();
//                                Container.users = user;
//                            } else {
//                                Toast.makeText(getContext(), "Lưu fail", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<UserResult> call, Throwable t) {
//                            System.out.println();
//                        }
//                    });
                }
                catch(Exception ex){
                    Toast.makeText(getContext(), "Fail!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imgCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK) {
//            System.out.println(data.getData());
            uriImage = data.getData();
            Picasso.get().load(data.getDataString()).resize(100, 100).centerCrop().into(imgAvt);

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
