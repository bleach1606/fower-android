package com.example.myflowerproject.fragment;

import android.content.Intent;
import android.os.Bundle;

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

import com.example.myflowerproject.R;
import com.example.myflowerproject.model.adapter.BasketHorizontalScrollAdapter;
import com.example.myflowerproject.model.adapter.BouquetGridLayoutAdapter;
import com.example.myflowerproject.model.adapter.CategoryAdapter;
import com.example.myflowerproject.model.adapter.SliderAdapter;
import com.example.myflowerproject.model.entity.CategoryModel;
import com.example.myflowerproject.model.entity.PreviewItemModel;
import com.example.myflowerproject.model.entity.SliderModel;

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

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;

    //// Banner Slider
    private ViewPager bannerSliderViewPager;
    private List<SliderModel> sliderModelList;
    private int currentPage = 2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;
    //// Banner Slider

    //// Basket Scroll Layout
    private TextView basketProductLayoutTitle;
    private Button btnBasketProductViewAll;
    private RecyclerView recyclerViewBasketProduct;
    //// Basket Scroll Layout

    ////Bouquet Grid Layout
    private TextView bouquetGridLayoutTitle;
    private Button btnBouquetGridLayoutViewAll;
    private GridView bouquetGridLayoutGridView;
    ////Bouquet Grid Layout

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home2, container, false);

        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManagerCategory = new LinearLayoutManager(getActivity());
        layoutManagerCategory.setOrientation(LinearLayoutManager.HORIZONTAL); //list view ngang
        categoryRecyclerView.setLayoutManager(layoutManagerCategory);

        List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
        categoryModelList.add(new CategoryModel(R.mipmap.home_icon,"Home"));
        categoryModelList.add(new CategoryModel(R.mipmap.color_icon,"Color"));
        categoryModelList.add(new CategoryModel(R.mipmap.bo_hoa_icon,"Bouquet"));
        categoryModelList.add(new CategoryModel(R.mipmap.hop_hoa_icon,"Box"));
        categoryModelList.add(new CategoryModel(R.mipmap.ke_hoa_icon,"Shelf"));
        categoryModelList.add(new CategoryModel(R.mipmap.gio_hoa_icon,"Basket"));
        categoryModelList.add(new CategoryModel(R.mipmap.lo_hoa_icon,"Vase"));
        categoryModelList.add(new CategoryModel(R.mipmap.hoa_cuoi_icon,"Wedding"));

        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

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
        //

        //// Basket Layout
        basketProductLayoutTitle = view.findViewById(R.id.basket_horizontal_scroll_layout_title);
        btnBasketProductViewAll = view.findViewById(R.id.basket_horizontal_scroll_layout_view_all_btn);
        recyclerViewBasketProduct = view.findViewById(R.id.basket_horizontal_scroll_layout_recycler_view);
        LinearLayoutManager linearLayoutManagerBasketProduct = new LinearLayoutManager(getContext());
        linearLayoutManagerBasketProduct.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewBasketProduct.setLayoutManager(linearLayoutManagerBasketProduct);

        //todo get du lieu
        List<PreviewItemModel> basketList = new ArrayList<>();
        basketList.add(new PreviewItemModel(R.mipmap.doc_do_bk34,"Congratulation","2.500.000 VND"));
        basketList.add(new PreviewItemModel(R.mipmap.doc_do_bk32,"Picture Red","3.000.000 VND"));
        basketList.add(new PreviewItemModel(R.mipmap.doc_hong_bk33,"Picture Pink","3.000.000 VND"));
        basketList.add(new PreviewItemModel(R.mipmap.doc_cam_bk3,"Picture Orange","2.000.000 VND"));
        basketList.add(new PreviewItemModel(R.mipmap.doc_vang_bk4,"Picture Yellow","2.500.000 VND"));

        BasketHorizontalScrollAdapter basketHorizontalScrollAdapter = new BasketHorizontalScrollAdapter(basketList);
        recyclerViewBasketProduct.setAdapter(basketHorizontalScrollAdapter);
        basketHorizontalScrollAdapter.notifyDataSetChanged();


        btnBasketProductViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), ListItemFragment.class);
                view.getContext().startActivity(intent);
            }
        });
        //// Basket Layout

        //// Bouquet Layout
        bouquetGridLayoutTitle = view.findViewById(R.id.bouquet_grid_layout_tittle);
        btnBouquetGridLayoutViewAll = view.findViewById(R.id.bouquet_grid_layout_view_all_btn);
        bouquetGridLayoutGridView = view.findViewById(R.id.bouquet_grid_layout_grid_view);

        List<PreviewItemModel> bouquetList = new ArrayList<>();
        bouquetList.add(new PreviewItemModel(R.mipmap.vuong_do_bq15,"Bouqet Red","800.000 VND"));
        bouquetList.add(new PreviewItemModel(R.mipmap.vuong_do_bq13,"Bouqet Red","500.000 VND"));
        bouquetList.add(new PreviewItemModel(R.mipmap.vuong_hong_bq27,"Bouqet Pink","1.000.000 VND"));
        bouquetList.add(new PreviewItemModel(R.mipmap.vuong_hong_bq17,"Bouqet Pink","800.000 VND"));

        BouquetGridLayoutAdapter bouquetGridLayoutAdapter = new BouquetGridLayoutAdapter(bouquetList);
        bouquetGridLayoutGridView.setAdapter(bouquetGridLayoutAdapter);


        //// Bouquet Layout
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
