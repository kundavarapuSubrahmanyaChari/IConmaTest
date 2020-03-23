package com.conma.jewel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class JewellaryActivity extends AppCompatActivity {
    int neckle[]={R.drawable.nackl1,
            R.drawable.nackl2,
            R.drawable.nackl3,
            R.drawable.neckl4};
    String names[]={"neckl4","neckl3","neckl2","neckl1"};
    String cost[]={"500","1000","1500","2000"};
    ListView recycler_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler_list = findViewById(R.id.jewel_list);
        MyAdapter ma=new MyAdapter();
        recycler_list.setAdapter(ma);
        recycler_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                String nm=names[arg2];
                String cst=cost[arg2];
                Intent in=new Intent(JewellaryActivity.this,Quantity.class);
                in.putExtra("name", nm);
                in.putExtra("category", "Neckles");
                in.putExtra("Price", cst);
                startActivity(in);
            }

        });
    }

    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return neckle.length;
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
            LayoutInflater lif=getLayoutInflater();
            v=lif.inflate(R.layout.recyler_item, null);
            ImageView item_icon=(ImageView)v.findViewById(R.id.item_icon);
            TextView item_name=(TextView)v.findViewById(R.id.item_name);
            TextView item_cost=(TextView)v.findViewById(R.id.item_cost);
            item_icon.setImageResource(neckle[position]);
            item_name.setText(names[position]);
            item_cost.setText(cost[position]);
            return v;
        }


    }
}
