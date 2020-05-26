package com.example.myflowerproject.fragment;

import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.myflowerproject.R;
import com.example.myflowerproject.model.adapter.BasketHorizontalScrollAdapter;
import com.example.myflowerproject.model.adapter.BouquetGridLayoutAdapter;
import com.example.myflowerproject.model.adapter.SliderAdapter;
import com.example.myflowerproject.model.entity.PreviewItemModel;
import com.example.myflowerproject.model.entity.SliderModel;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {

    private List<HomePageModel> homePageModelList;

    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        this.homePageModelList = homePageModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (homePageModelList.get(position).getType()){
            case 0:
                return HomePageModel.BANNERSLIDER;
            case 1:
                return HomePageModel.HORIZONTAL_PRODUCT_PREVIEW;
            case 2:
                return HomePageModel.GRID_PRODUCT_PREVIEW;
            default:
                return  -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType){
            case HomePageModel.BANNERSLIDER:
                View bannerSliderView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.slider_banner_layout,viewGroup,false);
                return new BannerSliderViewHolder(bannerSliderView);
            case HomePageModel.HORIZONTAL_PRODUCT_PREVIEW:
                View horizontalProductPreview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.basket_horizontal_scroll_layout,viewGroup,false);
                return new HorizontalProductViewHolder(horizontalProductPreview);
            case HomePageModel.GRID_PRODUCT_PREVIEW:
                View gridProductPreview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bouquet_grid_layout,viewGroup,false);
                return new GridProductViewHolder(gridProductPreview);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        String title;
        List<PreviewItemModel> previewItemModelList;
        switch (homePageModelList.get(position).getType()){
            case HomePageModel.BANNERSLIDER:
                List<SliderModel> sliderModelList = homePageModelList.get(position).getSliderModelList();
                ((BannerSliderViewHolder) viewHolder).setBannerSliderViewPager(sliderModelList);
                break;
            case HomePageModel.HORIZONTAL_PRODUCT_PREVIEW:
                title = homePageModelList.get(position).getTitle();
                previewItemModelList = homePageModelList.get(position).getPreviewItemModelList();
                ((HorizontalProductViewHolder) viewHolder).setHorizontalProductLayout(previewItemModelList,title);
                break;
            case HomePageModel.GRID_PRODUCT_PREVIEW:
                title = homePageModelList.get(position).getTitle();
                previewItemModelList = homePageModelList.get(position).getPreviewItemModelList();
                ((GridProductViewHolder) viewHolder).setGridProductLayout(previewItemModelList,title);
                break;
            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return homePageModelList.size();
    }

    public class BannerSliderViewHolder extends RecyclerView.ViewHolder {
        private ViewPager bannerSliderViewPager;
        private int currentPage = 2;
        private Timer timer;
        final private long DELAY_TIME = 3000;
        final private long PERIOD_TIME = 3000;
        public BannerSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerSliderViewPager = itemView.findViewById(R.id.banner_slider_view_pager);
        }
        private void setBannerSliderViewPager(final List<SliderModel> sliderModelList){
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
                        pageLooper(sliderModelList);
                    }
                }
            };
            bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

            startBannerSlideShow(sliderModelList);

            bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    pageLooper(sliderModelList);
                    stopBannerSlideShow();
                    if(event.getAction() == MotionEvent.ACTION_UP){
                        startBannerSlideShow(sliderModelList);
                    }
                    return false;
                }
            });
        }
        private void pageLooper(List<SliderModel> sliderModelList){
            if(currentPage == sliderModelList.size() - 1){
                currentPage = 3;
                bannerSliderViewPager.setCurrentItem(currentPage,false);
            }
            if(currentPage == 0){
                currentPage = sliderModelList.size() - 4;
                bannerSliderViewPager.setCurrentItem(currentPage,false);
            }
        }
        private void startBannerSlideShow(final List<SliderModel> sliderModelList){
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
    }

    public class HorizontalProductViewHolder extends RecyclerView.ViewHolder{

        private TextView basketProductLayoutTitle;
        private Button btnBasketProductViewAll;
        private RecyclerView recyclerViewBasketProduct;

        public HorizontalProductViewHolder(@NonNull View itemView) {
            super(itemView);
            basketProductLayoutTitle = itemView.findViewById(R.id.basket_horizontal_scroll_layout_title);
            btnBasketProductViewAll = itemView.findViewById(R.id.basket_horizontal_scroll_layout_view_all_btn);
            recyclerViewBasketProduct = itemView.findViewById(R.id.basket_horizontal_scroll_layout_recycler_view);

        }

        private void setHorizontalProductLayout(List<PreviewItemModel> previewItemModelList, String title){
            LinearLayoutManager linearLayoutManagerBasketProduct = new LinearLayoutManager(itemView.getContext());
            linearLayoutManagerBasketProduct.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerViewBasketProduct.setLayoutManager(linearLayoutManagerBasketProduct);
            BasketHorizontalScrollAdapter basketHorizontalScrollAdapter = new BasketHorizontalScrollAdapter(previewItemModelList);
            recyclerViewBasketProduct.setAdapter(basketHorizontalScrollAdapter);
            basketHorizontalScrollAdapter.notifyDataSetChanged();

            basketProductLayoutTitle.setText(title);
            if(previewItemModelList.size()>6){
                btnBasketProductViewAll.setVisibility(View.INVISIBLE);
            }
            else {
                btnBasketProductViewAll.setVisibility(View.VISIBLE);
            }

            btnBasketProductViewAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    public class GridProductViewHolder extends RecyclerView.ViewHolder{

        private TextView bouquetGridLayoutTitle;
        private Button btnBouquetGridLayoutViewAll;
        private GridView bouquetGridLayoutGridView;

        public GridProductViewHolder(@NonNull View itemView) {
            super(itemView);
            bouquetGridLayoutTitle = itemView.findViewById(R.id.bouquet_grid_layout_tittle);
            btnBouquetGridLayoutViewAll = itemView.findViewById(R.id.bouquet_grid_layout_view_all_btn);
            bouquetGridLayoutGridView = itemView.findViewById(R.id.bouquet_grid_layout_grid_view);

        }

        private void setGridProductLayout(List<PreviewItemModel> previewItemModelList, String title){
            BouquetGridLayoutAdapter bouquetGridLayoutAdapter = new BouquetGridLayoutAdapter(previewItemModelList);
            bouquetGridLayoutGridView.setAdapter(bouquetGridLayoutAdapter);

            bouquetGridLayoutTitle.setText(title);
            if(previewItemModelList.size()>4){
                btnBouquetGridLayoutViewAll.setVisibility(View.INVISIBLE);
            }
            else {
                btnBouquetGridLayoutViewAll.setVisibility(View.VISIBLE);
            }

            btnBouquetGridLayoutViewAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

}
