package com.example.myflowerproject.ui.cart;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myflowerproject.Container;
import com.example.myflowerproject.R;
import com.example.myflowerproject.model.api.ApiUtils;
import com.example.myflowerproject.model.api.GetImage;
import com.example.myflowerproject.model.results.OrderBillResult;
import com.example.myflowerproject.ui.cart.CartItemModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartItemAdapter extends RecyclerView.Adapter {

    private List<CartItemModel> cartItemModelList;
    private TextView cartTotalPrice;
    private TextView cartTotalAmount;
    private TextView cartTotalPriceTitle;
    private TextView cartDiscount;
    private EditText cartDiscountCode;

    double totalPrice = 0;
    double totalAmount = 0;
    int totalItems = 0;
    double discount = 0;

    public CartItemAdapter(List<CartItemModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (cartItemModelList.get(position).getType()){
            case 0:
                return CartItemModel.CART_ITEM;
            case 1:
                return CartItemModel.TOTAL_AMOUNT;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType){
            case CartItemModel.CART_ITEM:
                View cartItemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item_layout,viewGroup,false);
                return new CartItemViewHolder(cartItemView);
            case CartItemModel.TOTAL_AMOUNT:
                View cartTotalView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_total_amount_layout,viewGroup,false);
                return new CartTotalAmountViewHolder(cartTotalView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (cartItemModelList.get(position).getType()){
            case CartItemModel.CART_ITEM:
                int resource = Integer.parseInt(cartItemModelList.get(position).getCd().getFlowerProduct().getAvatar());
                String name = cartItemModelList.get(position).getCd().getFlowerProduct().getName();
                double price = cartItemModelList.get(position).getCd().getFlowerProduct().getPrice();
                double exPrice = cartItemModelList.get(position).getCd().getFlowerProduct().getPrice();
                ((CartItemViewHolder) viewHolder).setItemDetail(resource,name,price,exPrice,position);
                break;
            case CartItemModel.TOTAL_AMOUNT:
                ((CartTotalAmountViewHolder) viewHolder).setTotalDetail();
                break;
            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    class CartItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productName;
        private TextView productPrice;
        private TextView productExPrice;
        private Button btnCartRemoveItem;
        private Button btnAddProductQuantity;
        private Button btnSubProductQuantity;
        private EditText productQuantity;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.cart_item_product_image);
            productName = itemView.findViewById(R.id.cart_item_product_name);
            productPrice = itemView.findViewById(R.id.cart_item_product_price);
            productExPrice = itemView.findViewById(R.id.cart_item_product_ex_price);
            btnCartRemoveItem = itemView.findViewById(R.id.cart_item_product_remove_item_btn);
            btnAddProductQuantity = itemView.findViewById(R.id.cart_item_btn_add_quantity);
            btnSubProductQuantity = itemView.findViewById(R.id.cart_item_btn_sub_quantity);
            productQuantity = itemView.findViewById(R.id.cart_item_editText_quantity);
        }
        private void setItemDetail(int resource, String name, final double price, final double exPrice,int position){
            new GetImage(productImage).execute(""+resource);
            productName.setText(name);
            productPrice.setText("VND " + (int)price);
            productExPrice.setText("VND " + (int)exPrice);

            productQuantity.setText(""+cartItemModelList.get(position).getCd().getNumber());
            String str = productQuantity.getText().toString();
            final int[] quantity = {Integer.parseInt(str)};
            totalPrice += price*quantity[0];
            totalItems += quantity[0];;
            btnAddProductQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity[0]++;
                    totalPrice += price;
                    totalItems++;
                    setTotal();
                    String ans = Integer.toString(quantity[0]);
                    productQuantity.setText(ans);
                    cartItemModelList.get(position).getCd().setNumber(quantity[0]);
                    double sum = price * quantity[0];
                    double exSum = exPrice * quantity[0];
                    productPrice.setText("VND " + (int)sum);
                    productExPrice.setText("VND " + (int)exSum);
                    updateCart();
                }
            });
            btnSubProductQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(quantity[0]>1){
                        quantity[0]--;
                        totalPrice -= price;
                        totalItems--;
                        setTotal();
                    }
                    String ans = Integer.toString(quantity[0]);
                    productQuantity.setText(ans);
                    double sum = price * quantity[0];
                    double exSum = exPrice * quantity[0];
                    cartItemModelList.get(position).getCd().setNumber(quantity[0]);
                    productPrice.setText("VND " + (int)sum);
                    productExPrice.setText("VND " + (int)exSum);
                    updateCart();
                }
            });
            btnCartRemoveItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int qtt = Container.orderBill.getCartDetailList().get(position).getNumber();
                    System.out.println("SỐ LƯỢNG: " + qtt);
                    totalPrice -= Container.orderBill.getCartDetailList().get(position).getFlowerProduct().getPrice() * qtt;
                    System.out.println("SỐ LƯỢNG TỔNG: " + totalItems);
                    totalItems -= qtt;
                    System.out.println("SỐ LƯỢNG TỔNG: " + totalItems);
                    totalAmount = totalPrice;
                    cartTotalPrice.setText("VND " + (int)totalPrice);
                    cartTotalAmount.setText("VND " + (int)totalAmount);
                    if(totalItems > 1) {
                        cartTotalPriceTitle.setText("Price (" + totalItems + " items)");
                    }
                    else {
                        cartTotalPriceTitle.setText("Price (" + totalItems + " item)");
                    }
//                    cartItemModelList.remove(cartItemModelList.get(position));
                    CartItemModel cartItemModel = cartItemModelList.get(position);
                    cartItemModelList.remove(position);
                    Container.orderBill.getCartDetailList().remove(position);
                    updateCart();
                }
            });
        }
    }

    class CartTotalAmountViewHolder extends RecyclerView.ViewHolder {

        public CartTotalAmountViewHolder(@NonNull View itemView) {
            super(itemView);
            cartTotalPriceTitle = itemView.findViewById(R.id.textview_cart_price_total);
            cartTotalPrice = itemView.findViewById(R.id.cart_total_price);
            cartDiscount = itemView.findViewById(R.id.cart_total_discount);
            cartTotalAmount = itemView.findViewById(R.id.order_total_bill);
            cartDiscountCode = itemView.findViewById(R.id.cart_discount_code_editText);
        }
        private void setTotalDetail(){
            cartDiscount.setText("0%");
            cartTotalAmount.setText("VND " + (int)totalPrice);
            cartTotalPrice.setText("VND " + (int)totalPrice);
            if(totalItems > 1) {
                cartTotalPriceTitle.setText("Price (" + totalItems + " items)");
            }
            else {
                cartTotalPriceTitle.setText("Price (" + totalItems + " item)");
            }
            cartDiscountCode.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String discount_Code = s.toString();
//                    //nếu tồn tại mã giảm giá này, lấy dữ liệu về discountValue
//                    cartDiscountCode.setText(discount_Code);
//                    discount = 10;
//                    cartDiscount.setText(discount + "%");
//                    double totalAmount = totalPrice * (100 - discount) / 100;
//                    cartTotalAmount.setText("VND " + (int)totalAmount);
                }
            });
        }
    }

    public void setTotal(){
        totalAmount = totalPrice * (100 - discount) / 100;
        cartTotalPrice.setText("VND " + (int)totalPrice);
        cartTotalAmount.setText("VND " + (int)totalAmount);
        if(totalItems > 1) {
            cartTotalPriceTitle.setText("Price (" + totalItems + " items)");
        }
        else {
            cartTotalPriceTitle.setText("Price (" + totalItems + " item)");
        }
    }

    public void updateCart() {
        (ApiUtils.getOrderBillAPI()).updateOrderBill(Container.users.getToken(), Container.orderBill).enqueue(new Callback<OrderBillResult>() {
            @Override
            public void onResponse(Call<OrderBillResult> call, Response<OrderBillResult> response) {
                if (response.isSuccessful()) {
                } else {

                }
            }

            @Override
            public void onFailure(Call<OrderBillResult> call, Throwable t) {

            }
        });
    }

}
