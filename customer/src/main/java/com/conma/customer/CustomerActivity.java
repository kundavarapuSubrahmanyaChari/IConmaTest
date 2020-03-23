package com.conma.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.conma.itest.MainActivity;
import com.conma.itest.model.User;


public class CustomerActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "CustomerActivity" ;
    RelativeLayout vegitable_layout;
    RelativeLayout cars_layout;
    RelativeLayout jewel_layout;
    RelativeLayout users_layout;
    Intent intent = null;
    LinearLayout logout;
    TextView login_customer;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cars_layout = findViewById(R.id.cars_layout);
        vegitable_layout = findViewById(R.id.vegitable_layout);
        jewel_layout = findViewById(R.id.jewel_layout);
        users_layout = findViewById(R.id.users_layout);
        logout = findViewById(R.id.logout_layout);
        login_customer = findViewById(R.id.login_customer);
        user = getIntent().getExtras().getParcelable("UserData");

        Log.i(TAG, "User Feature " + user.getFeature());
        login_customer.setText("Welcome! to "+ user.getUsername());
        cars_layout.setOnClickListener(this);
        vegitable_layout.setOnClickListener(this);
        jewel_layout.setOnClickListener(this);
        users_layout.setOnClickListener(this);
        logout.setOnClickListener(this);
        /*
        * feature_list.add("Cars");
        feature_list.add("Vegitables");
        feature_list.add("Jewel");
        feature_list.add("Users");
        * */

        if (user.getFeature().equalsIgnoreCase("Cars")){
            cars_layout.setVisibility(View.VISIBLE);
            vegitable_layout.setVisibility(View.VISIBLE);
        }else if (user.getFeature().equalsIgnoreCase("Vegitables")){
            vegitable_layout.setVisibility(View.VISIBLE);
            jewel_layout.setVisibility(View.VISIBLE);
        }else if (user.getFeature().equalsIgnoreCase("Jewel")){
            jewel_layout.setVisibility(View.VISIBLE);
            cars_layout.setVisibility(View.VISIBLE);
        }else if (user.getFeature().equalsIgnoreCase("Users")){
            cars_layout.setVisibility(View.VISIBLE);
            vegitable_layout.setVisibility(View.VISIBLE);
            jewel_layout.setVisibility(View.VISIBLE);
        }else{
            cars_layout.setVisibility(View.GONE);
            vegitable_layout.setVisibility(View.GONE);
            jewel_layout.setVisibility(View.GONE);
            users_layout.setVisibility(View.GONE);
        }


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.cars_layout:
                Intent carsInent = new Intent();
                carsInent.setClassName(CustomerActivity.this, "com.conma.cars.CarsListActivity");
                startActivity(carsInent);
                break;
            case R.id.vegitable_layout:
                Intent vegIntent = new Intent();
                vegIntent.setClassName(CustomerActivity.this, "com.conma.vegitable.LearnVegetables");
                vegIntent.putExtra("UserName",user.getUsername());
                startActivity(vegIntent);
                break;
            case R.id.jewel_layout:
                Intent jewelInent = new Intent();
                jewelInent.setClassName(CustomerActivity.this, "com.conma.jewel.JewellaryActivity");
                startActivity(jewelInent);
                break;
            case R.id.users_layout:
                break;
            case R.id.logout_layout:
                try {
                    Intent myIntent = new Intent(this,Class.forName("com.conma.itest.MainActivity"));
                    myIntent.putExtra("logout","logout");
                    startActivity(myIntent );
                    finish();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }
}
