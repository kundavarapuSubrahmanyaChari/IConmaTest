package com.conma.jewel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.conma.jewel.model.Order;
import com.conma.jewel.salite.OrderDBHelper;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrder extends Activity {

    public static final String TAG = "PlaceOrder";
    Button add_order,Cancel;
    ListView orders_list;
    List<Order> order_list=new ArrayList<Order>();

    OrderDBHelper orderHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_order);
        orders_list = findViewById(R.id.orders_list);
        orderHelper = new OrderDBHelper(PlaceOrder.this);
        order_list = orderHelper.getAllOrders();

        Log.i(TAG, " Order List ::11 "+order_list.size());
        MyAdapter md=new MyAdapter(PlaceOrder.this,order_list);
        orders_list.setAdapter(md);
        add_order=(Button)findViewById(R.id.add_order);
        add_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent=new Intent(PlaceOrder.this,JewellaryActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Cancel=(Button)findViewById(R.id.cancel);
        Cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent=new Intent();
                intent.setClassName(PlaceOrder.this, "com.conma.customer.CustomerActivity");
                startActivity(intent);
                finish();
            }
        });
    }

    class MyAdapter extends BaseAdapter
    {
        List<Order> order_list;
        Context mContext;
        public MyAdapter(Context placeOrder, List<Order> order_list) {
            this.mContext = placeOrder;
            this.order_list = order_list;
            Log.i(TAG, " Order List::2 "+order_list.size());
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            Log.i(TAG, " Order List:: "+order_list.size());
            return order_list.size();
        }
        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }
        @Override
        public View getView(int position, View v, ViewGroup parent) {
            // TODO Auto-generated method stub
            LayoutInflater li=getLayoutInflater();
            v=li.inflate(R.layout.order_item, null);
            TextView item_name=(TextView)v.findViewById(R.id.item_name);
            TextView item_quantity=(TextView)v.findViewById(R.id.item_quantity);
            TextView item_price=(TextView)v.findViewById(R.id.item_price);
            Log.i(TAG, " Order ITEM NAME :: "+order_list.get(position).getItemName() );

            item_name.setText(order_list.get(position).getItemName());
            item_quantity.setText(order_list.get(position).getQuantity());
            item_price.setText(order_list.get(position).getPrice());
            return v;
        }

    }
}