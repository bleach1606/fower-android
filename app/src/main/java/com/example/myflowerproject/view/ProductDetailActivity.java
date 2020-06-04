package com.example.myflowerproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myflowerproject.Container;
import com.example.myflowerproject.R;
import com.example.myflowerproject.model.adapter.ProductDescriptionAdapter;
import com.example.myflowerproject.model.api.GetImage;
import com.example.myflowerproject.model.entity.Users;
import com.google.android.material.tabs.TabLayout;
import com.example.myflowerproject.model.entity.FlowerProducts;

public class ProductDetailActivity extends AppCompatActivity {
    private Users user;
    private TextView txtNameUser;
    private TextView txtEmailUser;

    private ImageView productImageView;

    private ViewPager productDescriptionViewpager;
    private TabLayout productDescriptionTab;

    private TextView productName;
    private TextView productAverageRatingMiniview;
    private TextView productTotalRatingMiniview;
    private TextView productPrice;
    private TextView productExPrice;

    private Button btnProductSubQuantity;
    private Button btnProductAddQuantity;
    private LinearLayout btnAddToCart;
    private EditText editTextProductQuantity;

    private TextView productDescription;

    private TextView averageRatings;
    private TextView totalRatings;
    private ProgressBar progressBar_5_Star;
    private ProgressBar progressBar_4_Star;
    private ProgressBar progressBar_3_Star;
    private ProgressBar progressBar_2_Star;
    private ProgressBar progressBar_1_Star;
    private TextView totalRatingsOf_5_Star;
    private TextView totalRatingsOf_4_Star;
    private TextView totalRatingsOf_3_Star;
    private TextView totalRatingsOf_2_Star;
    private TextView totalRatingsOf_1_Star;
    private LinearLayout yourStarContainer;
    private FlowerProducts fp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();
        fp = Container.getProductsById(id);
        ((TextView)findViewById(R.id.product_name)).setText(fp.getName());
        ((TextView)findViewById(R.id.product_price)).setText(""+fp.getPrice());
        ((TextView)findViewById(R.id.product_price)).setText(""+fp.getPrice());
        btnAddToCart = findViewById(R.id.add_to_cart);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quatity = Integer.parseInt(editTextProductQuantity.getText().toString());
                Toast.makeText(ProductDetailActivity.this, fp.getName()+" "+quatity, Toast.LENGTH_SHORT).show();
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productImageView = findViewById(R.id.product_image);
        new GetImage(productImageView).execute(fp.getAvatar());

        productDescriptionViewpager = findViewById(R.id.product_description_viewpager);
        productDescriptionTab = findViewById(R.id.product_description_tab);

        productDescriptionViewpager.setAdapter(new ProductDescriptionAdapter(getSupportFragmentManager(), productDescriptionTab.getTabCount()));

        productDescriptionViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDescriptionTab));
        productDescriptionTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDescriptionViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        productName = findViewById(R.id.product_name);
        productAverageRatingMiniview = findViewById(R.id.tv_product_rating_miniview);
        productTotalRatingMiniview = findViewById(R.id.product_total_rating_miniview);
        productPrice = findViewById(R.id.product_price);
        productExPrice = findViewById(R.id.product_ex_price);

        /////////////Quantity Product
        btnProductSubQuantity = findViewById(R.id.product_image_layout_btn_sub_quantity);
        btnProductAddQuantity = findViewById(R.id.product_image_layout_btn_add_quantity);
        editTextProductQuantity = findViewById(R.id.product_image_layout_editText_quantity);
        String str = editTextProductQuantity.getText().toString();
        final int[] quantity = {Integer.parseInt(str)};
        btnProductAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity[0]++;
                String ans = Integer.toString(quantity[0]);
                editTextProductQuantity.setText(ans);
            }
        });
        btnProductSubQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity[0]>1) quantity[0]--;
                String ans = Integer.toString(quantity[0]);
                editTextProductQuantity.setText(ans);
            }
        });
        /////////////Quantity Product

        productDescription = findViewById(R.id.tv_product_description);

        averageRatings = findViewById(R.id.rating_layout_average_ratings);
        totalRatings = findViewById(R.id.rating_layout_total_ratings);
        progressBar_5_Star = findViewById(R.id.rating_layout_progressBar_5_Star);
        progressBar_4_Star = findViewById(R.id.rating_layout_progressBar_4_Star);
        progressBar_3_Star = findViewById(R.id.rating_layout_progressBar_3_Star);
        progressBar_2_Star = findViewById(R.id.rating_layout_progressBar_2_Star);
        progressBar_1_Star = findViewById(R.id.rating_layout_progressBar_1_Star);
        totalRatingsOf_5_Star = findViewById(R.id.rating_layout_total_ratings_of_5star);
        totalRatingsOf_4_Star = findViewById(R.id.rating_layout_total_ratings_of_4star);
        totalRatingsOf_3_Star = findViewById(R.id.rating_layout_total_ratings_of_3star);
        totalRatingsOf_2_Star = findViewById(R.id.rating_layout_total_ratings_of_2star);
        totalRatingsOf_1_Star = findViewById(R.id.rating_layout_total_ratings_of_1star);

        ////////////////Your Ratings
        yourStarContainer = findViewById(R.id.rating_layout_your_star_container);

        for(int i = 0; i < 5; i ++){
            final int startPosition = i;
            yourStarContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setRating(startPosition);
                }
            });
        }
        ////////////////Your Ratings

    }

    private void setRating(int startPosition) {
        for(int i = 0; i < yourStarContainer.getChildCount(); i ++){
            ImageView starBtn = (ImageView)yourStarContainer.getChildAt(i);
            starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#D6D6D6")));
            if(i <= startPosition){
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#FF9999")));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        txtNameUser = findViewById(R.id.nav_header_home_fullname);
        txtEmailUser = findViewById(R.id.nav_header_home_email);
        Intent intent = getIntent();
        user = (Users) intent.getSerializableExtra("user");
//        Toast.makeText( getBaseContext(), user.toString(), Toast.LENGTH_SHORT).show();

//        txtNameUser.setText(user.getPeople().getName());
//        txtEmailUser.setText(user.getUsername());
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
            case R.id.app_bar_search:
                //to do: Search
                return true;
            case R.id.home_cart_icon:
                Intent intent = new Intent(ProductDetailActivity.this, MyCartActivity.class);
                startActivity(intent);
                return true;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

}
