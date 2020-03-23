package com.conma.cars;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.conma.cars.model.Vehicle;

import java.util.ArrayList;

public class CarsListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cars_list_layout);
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        ArrayList<Integer> images = new ArrayList<>();
        Uri jeep1 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.jeep1);
        images.add(R.raw.jeep1);
        images.add(R.raw.jeep2);
        images.add(R.raw.jeep3);
        vehicleList.add(new Vehicle(getResources().getString(R.string.brand1), getResources().getString(R.string.model1), getResources().getString(R.string.year1), getResources().getString(R.string.price1), getResources().getString(R.string.description1), R.raw.jeep1, images));
        images = new ArrayList<>();
        images.add(R.raw.mustang1);
        images.add(R.raw.mustang2);
        images.add(R.raw.mustang3);
        vehicleList.add(new Vehicle(getResources().getString(R.string.brand2), getResources().getString(R.string.model2), getResources().getString(R.string.year2), getResources().getString(R.string.price2), getResources().getString(R.string.description2), R.raw.mustang1, images));
        images = new ArrayList<>();
        images.add(R.raw.gmc1);
        images.add(R.raw.gmc2);
        images.add(R.raw.gmc3);
        vehicleList.add(new Vehicle(getResources().getString(R.string.brand3), getResources().getString(R.string.model3), getResources().getString(R.string.year3), getResources().getString(R.string.price3), getResources().getString(R.string.description3), R.raw.gmc1, images));
        images = new ArrayList<>();
        images.add(R.raw.mini1);
        images.add(R.raw.mini2);
        images.add(R.raw.mini3);
        vehicleList.add(new Vehicle(getResources().getString(R.string.brand4), getResources().getString(R.string.model4), getResources().getString(R.string.year4), getResources().getString(R.string.price4), getResources().getString(R.string.description4), R.raw.mini1, images));
        RecyclerView rec = findViewById(R.id.recycle);
        rec.setLayoutManager(new LinearLayoutManager(this));

        CustomCardViewAdapter adapter = new CustomCardViewAdapter(vehicleList);
        rec.setAdapter(adapter);
    }
}
