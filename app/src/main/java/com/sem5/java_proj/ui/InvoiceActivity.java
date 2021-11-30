package com.sem5.java_proj.ui;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sem5.java_proj.R;
import com.sem5.java_proj.controller.BillingController;
import com.sem5.java_proj.controller.OrderController;


public class InvoiceActivity extends AppCompatActivity {

    BillingController billingController;
    OrderController orderController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        orderController = OrderController.getInstance();
        billingController = BillingController.getInstance();
        fillCustomerInfo();
        fillProducts();

        FloatingActionButton invoiceBtn = findViewById(R.id.invoiceFab);
        invoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent startActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(startActivity);

            }
        });
    }

    private void fillCustomerInfo() {
        TextView name = findViewById(R.id.name);
        TextView email = findViewById(R.id.email);
        TextView phone = findViewById(R.id.phone);
        TextView address = findViewById(R.id.address);
        TextView postalCode = findViewById(R.id.postalCode);

        name.setText(billingController.getCustomer().getName());
        email.setText(billingController.getCustomer().getEmail());
        phone.setText(billingController.getCustomer().getPhone());
        address.setText(billingController.getCustomer().getAddress());
        postalCode.setText(billingController.getCustomer().getPostalCode());
    }

    private void fillProducts(){
        TextView totalCost = findViewById(R.id.totalCost);
        Intent i1=getIntent();
       String m= i1.getStringExtra("key1");
        //Log.i("info",str);
        totalCost.setText("₹"+billingController.getTotalPrice(m) );
        LinearLayout invoiceProducts = findViewById(R.id.invoice_products);
        for (int i=0; i < orderController.getProductsCount(); i++){

            LinearLayout invoiceProduct = (LinearLayout) getLayoutInflater().inflate(R.layout.invoice_product, null);

            TextView productName = (TextView)invoiceProduct.getChildAt(0);
            productName.setText(orderController.getProductName(i));

            TextView productQuantity = (TextView)invoiceProduct.getChildAt(1);
            productQuantity.setText("x" + orderController.getProductQuantity(i));

            TextView productPrice = (TextView)invoiceProduct.getChildAt(2);
            productPrice.setText("₹"+orderController.getInitialProductPrice(i));

            invoiceProducts.addView(invoiceProduct);
        }
    }
}
