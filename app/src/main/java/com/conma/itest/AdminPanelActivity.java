package com.conma.itest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.conma.itest.sqlite.CreateCustomerDB;

public class AdminPanelActivity extends AppCompatActivity {
TextView register_customer;
TextView logout_customer;
CreateCustomerDB customerDB;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_panel);
        register_customer = findViewById(R.id.register_customer);
        logout_customer = findViewById(R.id.logout_customer);
        sharedPreferences  = getSharedPreferences("IConmaTest",MODE_PRIVATE);
        myEdit = sharedPreferences.edit();

        customerDB = new
                CreateCustomerDB(this);

        register_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminPanelActivity.this, RegisterActivity.class));
            }
        });
        logout_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myEdit.putString("username","");
                myEdit.putString( "password","");
                myEdit.commit();
                startActivity(new Intent(AdminPanelActivity.this, MainActivity.class));
                finish();
            }
        });

    }
}
