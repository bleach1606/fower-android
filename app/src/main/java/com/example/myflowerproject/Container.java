package com.example.myflowerproject;

import android.app.Application;
import com.example.myflowerproject.model.entity.*;

import java.util.ArrayList;
import java.util.List;

public class Container extends Application {
    public static Users users;
    public static List<Category> listCategory;
    public static OrderBill orderBill;
    public static List<FlowerProducts> flowerProductsList;

    @Override
    public void onCreate() {
        super.onCreate();
        users = new Users();
        listCategory = new ArrayList<Category>();
        flowerProductsList = new ArrayList<>();
    }

    static public FlowerProducts getProductsById(int id){
        for (Category cm: listCategory){
            for(FlowerProducts fp: cm.getFlowerProductsList()){
                if(id==fp.getId()) return fp;
            }
        }
        return null;
    }
}
