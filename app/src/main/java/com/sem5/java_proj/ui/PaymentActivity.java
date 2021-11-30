package com.sem5.java_proj.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.sem5.java_proj.R;
import com.sem5.java_proj.controller.BillingController;

public class PaymentActivity extends AppCompatActivity {
    EditText cardNumber;
    EditText cvv;
    EditText expDate;
    EditText name;
    EditText address;
    EditText email;
    EditText phone;
    EditText postalCode;
    BillingController billingController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        billingController = BillingController.getInstance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onPaymentClick(view)) {
                    Intent invoiceActivity = new Intent(PaymentActivity.this, InvoiceActivity.class);
                    Intent paymentActivity = getIntent();
                    String str=paymentActivity.getStringExtra("key");
                    invoiceActivity.putExtra("key1",str);
                    PaymentActivity.this.startActivity(invoiceActivity);
                }
            }
        });
    }

    public void toCheckoutActivtiy(View view){
        super.onBackPressed();
    }

    public boolean onPaymentClick(View view){
        fillViews();
        if (isEmpty()){
            Snackbar.make(view, "Please fill the required fields", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
            return false;
        }

        billingController.createCustomer(name.getText().toString(), email.getText().toString(),address.getText().toString(),
                postalCode.getText().toString(), phone.getText().toString());

        return true;

    }

    public boolean isEmpty(){
        if (cardNumber.getText().toString().matches(""))
            return true;
        if (cvv.getText().toString().matches(""))
            return true;
        if (expDate.getText().toString().matches(""))
            return true;
        if (name.getText().toString().matches(""))
            return true;
        if (address.getText().toString().matches(""))
            return true;
        if (email.getText().toString().matches(""))
            return true;
        if (phone.getText().toString().matches(""))
            return true;
        if (postalCode.getText().toString().matches(""))
            return true;

        return false;
    }


    public void fillViews(){
        cardNumber = findViewById(R.id.cardNumber);
        cvv = findViewById(R.id.cardNumber);
        expDate = findViewById(R.id.expDate);
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.telephone);
        postalCode = findViewById(R.id.postalCode);
    }


}
