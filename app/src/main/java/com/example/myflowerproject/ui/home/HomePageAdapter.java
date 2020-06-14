package com.example.myflowerproject.ui.home;

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
import com.example.myflowerproject.model.entity.FlowerProducts;
import com.example.myflowerproject.view.Activity_ListItem;

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
        List<FlowerProducts> flowerProductsList;
        int typeProduct;
        switch (homePageModelList.get(position).getType()){
            case HomePageModel.BANNERSLIDER:
                List<SliderModel> sliderModelList = homePageModelList.get(position).getSliderModelList();
                ((BannerSliderViewHolder) viewHolder).setBannerSliderViewPager(sliderModelList);
                break;
            case HomePageModel.HORIZONTAL_PRODUCT_PREVIEW:
                title = homePageModelList.get(position).getTitle();
                typeProduct = homePageModelList.get(position).getTypeProduct();
                flowerProductsList = homePageModelList.get(position).getFlowerProductsList();
                ((HorizontalProductViewHolder) viewHolder).setHorizontalProductLayout(flowerProductsList,title,typeProduct);
                break;
            case HomePageModel.GRID_PRODUCT_PREVIEW:
                title = homePageModelList.get(position).getTitle();
                typeProduct = homePageModelList.get(position).getTypeProduct();
                flowerProductsList = homePageModelList.get(position).getFlowerProductsList();
                ((GridProductViewHolder) viewHolder).setGridProductLayout(flowerProductsList,title,typeProduct);
                ((GridProductViewHolder)viewHolder).setIsRecyclable(true);
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
            HomeFragment.SliderAdapter sliderAdapter = new HomeFragment.SliderAdapter(sliderModelList);
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

        private void setHorizontalProductLayout(List<FlowerProducts> flowerProductsList, String title, final int typeProduct){
            LinearLayoutManager linearLayoutManagerBasketProduct = new LinearLayoutManager(itemView.getContext());
            linearLayoutManagerBasketProduct.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerViewBasketProduct.setLayoutManager(linearLayoutManagerBasketProduct);
            BasketHorizontalScrollAdapter basketHorizontalScrollAdapter = new BasketHorizontalScrollAdapter(flowerProductsList);
            recyclerViewBasketProduct.setAdapter(basketHorizontalScrollAdapter);
            basketHorizontalScrollAdapter.notifyDataSetChanged();

            basketProductLayoutTitle.setText(title);

            btnBasketProductViewAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), Activity_ListItem.class);
                    intent.putExtra("product type", typeProduct);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    public class GridProductViewHolder extends RecyclerView.ViewHolder{

        private TextView bouquetGridLayoutTitle;
        private GridView bouquetGridLayoutGridView;

        public GridProductViewHolder(@NonNull View itemView) {
            super(itemView);
//            bouquetGridLayoutTitle = itemView.findViewById(R.id.bouquet_grid_layout_tittle);
            bouquetGridLayoutGridView = itemView.findViewById(R.id.bouquet_grid_layout_grid_view);
            bouquetGridLayoutGridView.smoothScrollBy(1, 1);
        }

        private void setGridProductLayout(List<FlowerProducts> flowerProductsList, String title, final int typeProduct){
//            bouquetGridLayoutTitle.setText(title);
            BouquetGridLayoutAdapter bouquetGridLayoutAdapter = new BouquetGridLayoutAdapter(flowerProductsList);
            bouquetGridLayoutGridView.setAdapter(bouquetGridLayoutAdapter);
            bouquetGridLayoutAdapter.notifyDataSetChanged();
        }
    }

}
