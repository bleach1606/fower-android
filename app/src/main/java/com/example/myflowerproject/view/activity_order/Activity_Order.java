package com.example.myflowerproject.view.activity_order;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myflowerproject.Container;
import com.example.myflowerproject.R;
import com.example.myflowerproject.constant.Constant;
import com.example.myflowerproject.model.api.ApiUtils;
import com.example.myflowerproject.model.entity.CartDetail;
import com.example.myflowerproject.model.entity.OrderBill;
import com.example.myflowerproject.model.entity.Payment;
import com.example.myflowerproject.model.entity.Users;
import com.example.myflowerproject.model.results.OrderBillResult;
import com.example.myflowerproject.view.Activity_Home;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myflowerproject.paypal.ConfigPaypal.PAYPAL_CLIENT_ID;

public class Activity_Order extends AppCompatActivity {
    private Users user;
    private TextView txtNameUser;
    private TextView txtEmailUser;

    private RecyclerView orderCartRecyclerView;
    private OrderCartItemAdapter orderCartItemAdapter;

    private EditText edtFullName;
    private EditText edtTel;
    private EditText edtAddress;

    private RadioButton rbStandardDelivery;
    private RadioButton rbExpressDelivery;
    private RadioButton rbPaypal;
    private RadioButton rbCash;

    private TextView tvTotalProductPrice;
    private TextView tvShippingFee;
    private TextView tvTotalBill;
    private Button btnOrderConfirm;

    private static final double STANDARD_DELIVERY = 20000;
    private static final double EXPRESS_DELIVERY = 35000;

    private static final int PAYPAL_REQUEST_CODE = 7777;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PAYPAL_CLIENT_ID);
    Button btnPayNow;
    EditText edtAmount;

    String amount = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtFullName = findViewById(R.id.information_people_full_name);
        edtTel = findViewById(R.id.information_people_tel);
        edtAddress = findViewById(R.id.information_people_address);

        edtFullName.setText(Container.users.getPeople().getFirstName() + " " + Container.users.getPeople().getLastName());
        edtTel.setText(Container.users.getPeople().getPhoneNumber());
        edtAddress.setText(Container.users.getPeople().getAddress());

        rbCash = findViewById(R.id.cash_radiobutton);
        rbPaypal = findViewById(R.id.paypal_radiobutton);
        rbStandardDelivery = findViewById(R.id.order_standard_delivery_radioButton);
        rbExpressDelivery = findViewById(R.id.order_express_delivery_radioButton);

        tvTotalProductPrice = findViewById(R.id.order_total_product_price);
        tvShippingFee = findViewById(R.id.order_shipping_fee);
        tvTotalBill = findViewById(R.id.order_total_bill);
        btnOrderConfirm = findViewById(R.id.order_confirm_btn);

        rbCash.setChecked(true);
        rbStandardDelivery.setChecked(true);

        orderCartRecyclerView = findViewById(R.id.order_cart_recyclerview);
        LinearLayoutManager layoutManagerCategory = new LinearLayoutManager(Activity_Order.this);
        layoutManagerCategory.setOrientation(LinearLayoutManager.HORIZONTAL); //list view ngang
        orderCartRecyclerView.setLayoutManager(layoutManagerCategory);

        orderCartItemAdapter = new OrderCartItemAdapter(Container.orderBill.getCartDetailList());
        orderCartRecyclerView.setAdapter(orderCartItemAdapter);
        orderCartItemAdapter.notifyDataSetChanged();

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        startService(intent);

        final double[] total = {0};
        for(CartDetail cd: Container.orderBill.getCartDetailList()){
            total[0] += cd.getFlowerProduct().getPrice() * cd.getNumber();
        }

        tvTotalProductPrice.setText(total[0] + " VND");

        total[0] += STANDARD_DELIVERY;
        final int[] typeDelivery = {1};
        final String[] typePayment = {"Cash"};

        rbPaypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbCash.setChecked(false);
                rbPaypal.setChecked(true);
                if(typePayment[0] == "Cash"){
                    typePayment[0] = "Paypal";
                }
            }
        });

        rbCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbCash.setChecked(true);
                rbPaypal.setChecked(false);
                if(typePayment[0] == "Paypal"){
                    typePayment[0] = "Cash";
                }
            }
        });

        rbStandardDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbStandardDelivery.setChecked(true);
                rbExpressDelivery.setChecked(false);
                if(typeDelivery[0] == 2){
                    total[0] += STANDARD_DELIVERY - EXPRESS_DELIVERY;
                    typeDelivery[0] = 1;
                    tvTotalBill.setText(total[0] + " VND");
                }
                tvShippingFee.setText(STANDARD_DELIVERY + " VND");
            }
        });

        rbExpressDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbStandardDelivery.setChecked(false);
                rbExpressDelivery.setChecked(true);
                if(typeDelivery[0] == 1){
                    total[0] -= STANDARD_DELIVERY - EXPRESS_DELIVERY;
                    typeDelivery[0] = 2;
                    tvTotalBill.setText(total[0] + " VND");
                }
                tvShippingFee.setText(EXPRESS_DELIVERY + " VND");
            }
        });

        tvTotalBill.setText(total[0] + " VND");
        tvShippingFee.setText(STANDARD_DELIVERY + " VND");

        btnOrderConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Container.orderBill.setPayment(new Payment(total[0],typePayment[0],true));
                Container.orderBill.setReceiverAddress(edtAddress.getText().toString());
                Container.orderBill.setReceiverName(edtFullName.getText().toString());
                Container.orderBill.setReceiverTel(edtTel.getText().toString());
                Container.orderBill.setStatus(Constant.OrderStatus.WAIT.getId());
                Container.orderBill.setActive(true);
//                Container.orderBill.setUsers(Container.users);
                Container.orderBill.setId(0);
                if (typePayment[0] == "Paypal") {
                    processPayment();
                } else {
                    sendPayMent();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    public void sendPayMent() {
        (ApiUtils.getOrderBillAPI()).updateOrderBill(Container.users.getToken(), Container.orderBill).enqueue(new Callback<OrderBillResult>() {
            @Override
            public void onResponse(Call<OrderBillResult> call, Response<OrderBillResult> response) {
                System.out.println(Container.users.toString());
                System.out.println(Container.orderBill.toString());
                if (response.isSuccessful()) {
                    Container.orderBill = response.body().getOrderBill();
                    Toast.makeText(Activity_Order.this,"Bạn đã đặt hàng thành công",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Activity_Order.this, Activity_Home.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Activity_Order.this, "Đặt hàng thất bại, vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<OrderBillResult> call, Throwable t) {

            }
        });
    }

    private void processPayment() {
        amount = "0.01";
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(amount)),"USD",
                "Purchase Goods",PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
        startActivityForResult(intent,PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYPAL_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null){
                    try {
                        String paymentDetails = confirmation.toJSONObject().toString(4);
                        startActivity(new Intent(this, PaymentDetails.class)
                                .putExtra("Payment Details",paymentDetails)
                                .putExtra("Amount",amount));
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED)
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID)
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
    }
}
