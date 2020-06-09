package com.example.myflowerproject.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myflowerproject.Container;
import com.example.myflowerproject.R;
import com.example.myflowerproject.model.entity.Category;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    private ViewPager bannerSliderViewPager;
    private List<SliderModel> sliderModelList;
    private List<HomePageModel> homePageModelList = new ArrayList<>();
    private HomePageAdapter adapter;
    private RecyclerView testing;
    private int currentPage = 2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home_3, container, false);

        //BannerSlider
        bannerSliderViewPager = view.findViewById(R.id.banner_slider_view_pager);
        sliderModelList = new ArrayList<SliderModel>();
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
        setListFlower(-1);

        testing = view.findViewById(R.id.testing);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);
        adapter = new HomePageAdapter(homePageModelList);
        testing.setAdapter(adapter);

        ((TabLayout)view.findViewById(R.id.category_tab_layout)).addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setListFlower(tab.getPosition()-1);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
        return view;
    }

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

    private void setListFlower(int type){
        homePageModelList.clear();
        if(type==-1){
            for(Category category : Container.listCategory){
                HomePageModel homePageModel = new HomePageModel(1, category.getCategoryName(), category.getFlowerProductsList() , category.getType());
                homePageModelList.add(homePageModel);
            }
        }else {
            for (Category category : Container.listCategory) {
                if (category.getType() == type) {
                    HomePageModel homePageModel = new HomePageModel(2, category.getCategoryName(), category.getFlowerProductsList(), category.getType());
                    homePageModelList.add(homePageModel);
                    return;
                }
            }
        }
    }

    public static class SliderAdapter extends PagerAdapter {

        private List<SliderModel> sliderModelList;

        public SliderAdapter(List<SliderModel> sliderModelList) {
            this.sliderModelList = sliderModelList;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slider_layout, container,false);
            ImageView banner = view.findViewById(R.id.banner_slider);
            banner.setImageResource(sliderModelList.get(position).getBanner());
            container.addView(view,0);
            return view;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return sliderModelList.size();
        }
    }
}
