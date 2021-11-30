package com.sem5.java_proj.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.sem5.java_proj.R;
import com.sem5.java_proj.controller.AdminController;
import com.sem5.java_proj.controller.OrderController;

public class MainActivity extends AppCompatActivity {
    AdminController adminController;
    OrderController orderController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set actionbar as a toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AdminController.instantiateApp();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        adminController = AdminController.getInstance();
        orderController = OrderController.getInstance();
        FloatingActionButton fab = findViewById(R.id.mainActivityFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (orderController.getBasket().getProductsToOrder().isEmpty()){
                    Snackbar.make(view, "Please add at least one product", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    return;
                }
                Intent checkoutActivity = new Intent(MainActivity.this, CheckoutActivity.class);
                MainActivity.this.startActivity(checkoutActivity);
            }
        });


    }
    //adding the item into the basket
    public void addBasket(View view){
        int tag = Integer.parseInt(view.getTag().toString());
        orderController.addProduct(tag);
        view.setBackgroundResource(R.drawable.ic_done);
    }
}
