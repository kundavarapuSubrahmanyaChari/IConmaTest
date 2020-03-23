package com.conma.cars;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.conma.cars.fragments.CarFragment;
import com.conma.cars.fragments.ImagesFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CarInfoActivity extends AppCompatActivity {

    private ArrayList<Integer> images;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_main_layout);
        Bundle args = getIntent().getExtras();
        String brand = args.getString("brand");
        String model = args.getString("model");
        String year = args.getString("year");
        String price = args.getString("price");
        String desc = args.getString("desc");
        images = args.getIntegerArrayList("images");

        TextView brandView = findViewById(R.id.vehicleBrand);
        TextView modelView = findViewById(R.id.vehicleModel);
        TextView yearView = findViewById(R.id.vehicleYear);
        TextView priceView = findViewById(R.id.vehiclePrice);
        TextView descriptionView = findViewById(R.id.vehicleDesc);
        ImageView imageView = findViewById(R.id.imageView);

        Picasso.with(this).load(images.get(0)).into(imageView);

        brandView.setText(brand);
        modelView.setText(model);
        yearView.setText(year);
        priceView.setText(price);
        descriptionView.setText(desc);
    }
    private void loadFragment(Fragment fragment, Bundle args) {
        fragment.setArguments(args);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }


}
