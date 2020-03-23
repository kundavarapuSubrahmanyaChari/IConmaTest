package com.conma.itest;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.conma.itest.model.User;
import com.conma.itest.sqlite.CreateCustomerDB;
import com.conma.itest.util.Util;

import java.util.ArrayList;

import io.realm.Realm;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "RegisterActivity";
    EditText edt_user_name;
    EditText edt_password;
    AppCompatSpinner select_usermodule;
    Button btn_register;
    CreateCustomerDB customerDB;
    ArrayList<String> feature_list = new ArrayList<String>();
    String feature_selected ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);
        edt_user_name = findViewById(R.id.edt_user_name);
        edt_password = findViewById(R.id.edt_password);
        select_usermodule = findViewById(R.id.select_usermodule);
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
        feature_list.add("Select Feature");
        feature_list.add("Cars");
        feature_list.add("Vegitables");
        feature_list.add("Jewel");
        feature_list.add("Users");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_spinner,
                feature_list);
        adapter.setDropDownViewResource(R.layout.list_item_spinner_dropdown);
        select_usermodule.setAdapter(adapter);
        customerDB = new CreateCustomerDB(RegisterActivity.this);

        select_usermodule.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                feature_selected = feature_list.get(position);

                Log.i(TAG, "Selected Feature is : "+  feature_selected);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Log.i(TAG,"USER COUNT "+customerDB.getUsersCount());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_register:
                String _mUsername = edt_user_name.getText().toString();
                String _mPassword = edt_password.getText().toString();
                validforRegister(_mUsername, _mPassword, feature_selected );
                break;
        }
    }

    private void validforRegister(final String _mUsername,final String _mPassword, final String _mFeature) {
        if(TextUtils.isEmpty(_mUsername)) {
            edt_user_name.setError("Enter username");
        } else if(TextUtils.isEmpty(_mPassword)) {
            edt_password.setError("Enter valid password");
        }else if(feature_selected.equals("Select Feature")) {
            Toast.makeText(this, "Please select feature for the user", Toast.LENGTH_SHORT).show();
        }else{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    customerDB.insertdata(_mUsername,_mPassword,_mFeature);
                    String encrypt_pass = Util.md5(_mPassword);
                    User user = new User(_mUsername,encrypt_pass,_mFeature);
                    customerDB.insertNote(user);
                    Toast.makeText(RegisterActivity.this, "Customer created successfully.",Toast.LENGTH_LONG).show();
                    Log.i(TAG,"USER COUNT "+customerDB.getUsersCount());
                    onBackPressed();
                    finish();
                }
            });

        }
    }


}
