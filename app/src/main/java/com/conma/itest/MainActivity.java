package com.conma.itest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.conma.itest.model.User;
import com.conma.itest.sqlite.CreateCustomerDB;
import com.conma.itest.util.Util;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    EditText edt_user;
    EditText edt_passp;
    Button btn_Login;
    CreateCustomerDB customerDB;
    List<User> userList = new ArrayList<User>();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    String userName;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        sharedPreferences  = getSharedPreferences("IConmaTest",MODE_PRIVATE);
        myEdit = sharedPreferences.edit();
        edt_user = findViewById(R.id.edt_user);
        edt_passp = findViewById(R.id.edt_passp);
        btn_Login = findViewById(R.id.btn_Login);
        btn_Login.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle !=null)
        {
          String logout =  bundle.getString("logout");
            Log.i(TAG, "Logout :: "+logout);
            if (logout.equalsIgnoreCase(logout)){
                myEdit.putString("username","");
                myEdit.putString( "password","");
                myEdit.commit();


            }
        }



        customerDB= new CreateCustomerDB(this);

        userList = customerDB.getAllUsers();



        userName = sharedPreferences.getString("username", "");
        password = sharedPreferences.getString("password", "");

        if (userName.equals("admin") && password.equals("admin")){
            Intent adminIntent = new Intent(MainActivity.this, AdminPanelActivity.class);
            startActivity(adminIntent);
        }else {
            User user = customerDB.getUser(userName);
            String encrypt= Util.md5(password);
            if (user != null && encrypt.equals(user.getPassword()))
            {
//                Toast.makeText(this, "Valid user "+ user.getUsername() +" :: "+user.getFeature(), Toast.LENGTH_SHORT).show();
                Intent customerInent = new Intent();
                customerInent.setClassName(MainActivity.this, "com.conma.customer.CustomerActivity");
                customerInent.putExtra("UserData",user);
                startActivity(customerInent);
            }else {
//                Toast.makeText(this, "Invalid user", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        edt_user.setText("");
        edt_passp.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Login:
                String mUserName = edt_user.getText().toString();
                String mPassword = edt_passp.getText().toString();
                if (mUserName.equals("admin") && mPassword.equals("admin")){
                    myEdit.putString("username",mUserName);
                    myEdit.putString( "password",mPassword);
                    myEdit.commit();

                    Intent adminIntent = new Intent(MainActivity.this, AdminPanelActivity.class);
                    startActivity(adminIntent);

                }else{
                    if (TextUtils.isEmpty(mUserName))
                    {
                        Toast.makeText(this, "Enter username", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(mPassword)){
                        Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
                    }else {
                        myEdit.putString("username",mUserName);
                        myEdit.putString("password",mPassword);
                        myEdit.commit();
                        User user = customerDB.getUser(mUserName);
                        String encrypt= Util.md5(mPassword);
                        Log.i(TAG, "Password :: "+encrypt +" :; "+ user.getPassword());
                        if (user != null && encrypt.equals(user.getPassword()))
                        {
//                            Toast.makeText(this, "Valid user "+ user.getUsername() +" :: "+user.getFeature(), Toast.LENGTH_SHORT).show();

                            Intent customerInent = new Intent();
                            customerInent.setClassName(MainActivity.this, "com.conma.customer.CustomerActivity");
                            customerInent.putExtra("UserData",user);
                            startActivity(customerInent);
                        }else {
                            Toast.makeText(this, "Invalid user", Toast.LENGTH_SHORT).show();
                        }
                    }
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
