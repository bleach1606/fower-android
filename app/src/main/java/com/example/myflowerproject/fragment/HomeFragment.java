package com.example.myflowerproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myflowerproject.Container;
import com.example.myflowerproject.R;
import com.example.myflowerproject.model.adapter.BasketHorizontalScrollAdapter;
import com.example.myflowerproject.model.adapter.BouquetGridLayoutAdapter;
import com.example.myflowerproject.model.adapter.CategoryAdapter;
import com.example.myflowerproject.model.adapter.SliderAdapter;
import com.example.myflowerproject.model.api.ApiUtils;
import com.example.myflowerproject.model.entity.CategoryModel;
import com.example.myflowerproject.model.entity.PreviewItemModel;
import com.example.myflowerproject.model.entity.SliderModel;
import com.example.myflowerproject.model.entity.Users;
import com.example.myflowerproject.model.results.CategoryResult;
import com.example.myflowerproject.view.HomeActivity;
import com.example.myflowerproject.model.api.*;
import com.example.myflowerproject.model.entity.*;
import com.google.gson.Gson;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private int type;

    public HomeFragment() {

        // Required empty public constructor
    }
    public HomeFragment(int i) {
        this.type = i;
    }

//    private RecyclerView categoryRecyclerView;
//    private CategoryAdapter categoryAdapter;

    //// Banner Slider
    private ViewPager bannerSliderViewPager;
    private List<SliderModel> sliderModelList;
    private int currentPage = 2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;
    private List<CategoryModel> categoryModelList;
    private CategoryAPI categoryAPI;
    //// Banner Slider

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home_3, container, false);

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //BannerSlider
        bannerSliderViewPager = view.findViewById(R.id.banner_slider_view_pager);

        sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.mipmap.banner_tet_holiday));
        sliderModelList.add(new SliderModel(R.mipmap.banner_christmas_day));

        sliderModelList.add(new SliderModel(R.mipmap.banner_women_day));
        sliderModelList.add(new SliderModel(R.mipmap.banner_valentine_day));
        sliderModelList.add(new SliderModel(R.mipmap.banner_tet_holiday));
        sliderModelList.add(new SliderModel(R.mipmap.banner_christmas_day));

        sliderModelList.add(new SliderModel(R.mipmap.banner_women_day));
        sliderModelList.add(new SliderModel(R.mipmap.banner_valentine_day));

        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(sliderAdapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int i) {
                currentPage = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if(i == ViewPager.SCROLL_STATE_IDLE){
                    pageLooper();
                }
            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

        startBannerSlideShow();

        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopBannerSlideShow();
                if(event.getAction() == MotionEvent.ACTION_UP){
                    startBannerSlideShow();
                }
                return false;
            }
        });
        List<HomePageModel> homePageModelList;
        if(type<1){
            homePageModelList = new ArrayList<>();
            for(CategoryModel categoryModel: Container.listCategory){
                List<PreviewItemModel> list = new ArrayList<>();
                for(FlowerProducts fp: categoryModel.getFlowerProductsList()){
                    list.add(new PreviewItemModel(fp));
                }
                HomePageModel homePageModel = new HomePageModel(1, categoryModel.getCategoryName(), list , categoryModel.getId());
                homePageModelList.add(homePageModel);
            }
        }else{
            homePageModelList = new ArrayList<>();
            for(CategoryModel categoryModel: Container.listCategory){
                if(categoryModel.getId()==type){
                    List<PreviewItemModel> list = new ArrayList<>();
                    for(FlowerProducts fp: categoryModel.getFlowerProductsList()){
                        list.add(new PreviewItemModel(fp));
                    }
                    HomePageModel homePageModel = new HomePageModel(2, categoryModel.getCategoryName(), list , categoryModel.getId());
                    homePageModelList.add(homePageModel);
                }
            }
        }

        RecyclerView testing = view.findViewById(R.id.testing);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);

        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        testing.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //////////////////////////

        return view;
    }

    //Banner Slider
    private void pageLooper(){
        if(currentPage == sliderModelList.size() - 1){
            currentPage = 3;
            bannerSliderViewPager.setCurrentItem(currentPage,false);
        }
        if(currentPage == 0){
            currentPage = sliderModelList.size() - 4;
            bannerSliderViewPager.setCurrentItem(currentPage,false);
        }
    }


    private void startBannerSlideShow(){
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if(currentPage >= sliderModelList.size()){
                    currentPage = 1;
                }
                bannerSliderViewPager.setCurrentItem(currentPage++,true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_TIME, PERIOD_TIME);
    }

    private void stopBannerSlideShow(){
        timer.cancel();
    }
    //

}
