package com.conma.jewel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatSpinner;

import com.conma.itest.model.User;
import com.conma.jewel.model.Order;
import com.conma.jewel.salite.OrderDBHelper;


public class Quantity extends Activity {
	String quntity[]={"1","2","3","4","5","6","7","8","9"};
	Button proceed;

	AppCompatSpinner appcompact_spinner;
	String spinner_item;
	OrderDBHelper orderHelper;
	Bundle bundle;
	String name = "";
	String category = "";
	String price = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quantity_layout);
		appcompact_spinner= findViewById(R.id.appcompact_spinner);
		proceed = findViewById(R.id.proceed);
		orderHelper = new OrderDBHelper(Quantity.this);
		 bundle= getIntent().getExtras();
		 name =bundle.getString("name");
		 category =bundle.getString("category");
		 price =bundle.getString("Price");

		ArrayAdapter<String> adp=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,quntity);
		appcompact_spinner.setAdapter(adp);
		appcompact_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				spinner_item = appcompact_spinner.getSelectedItem().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		proceed.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Order order = new Order(name,category,price,spinner_item);
				orderHelper.insertNote(order);
					Intent in=new Intent(Quantity.this,PlaceOrder.class);
					startActivity(in);
			}
		});
	}

}
