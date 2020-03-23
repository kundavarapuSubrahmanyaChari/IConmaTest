package com.conma.cars;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.conma.cars.model.Vehicle;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomCardViewAdapter extends RecyclerView.Adapter {
    private ArrayList<Vehicle> vehicleArrayList;
    private boolean isTwoPane;

    public CustomCardViewAdapter(ArrayList<Vehicle> vehicleArrayList) {
        this.vehicleArrayList = vehicleArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_item, null);
//        this.isTwoPane = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_view_inv, null).findViewById(R.id.vehicle_container) != null;

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int i) {
        final Vehicle vehicle = vehicleArrayList.get(i);
        ((CustomViewHolder) holder).brand.setText(vehicle.getBrand());
        ((CustomViewHolder) holder).model.setText(vehicle.getModel());
        ((CustomViewHolder) holder).year.setText(vehicle.getYear());
        ((CustomViewHolder) holder).price.setText(vehicle.getPrice());
        Picasso.with(((CustomViewHolder) holder).view.getContext()).load(vehicle.getThumbnailID()).into(((CustomViewHolder) holder).thumbnail);
        //.setImageResource();
        ((CustomViewHolder) holder).card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Intent activityIntent = new Intent(activity, CarInfoActivity.class);
                Bundle args = new Bundle();
                args.putString("brand", vehicle.getBrand());
                args.putString("model", vehicle.getModel());
                args.putString("year", vehicle.getYear());
                args.putString("price", vehicle.getPrice());
                args.putString("desc", vehicle.getDescription());
                args.putIntegerArrayList("images", vehicle.getImages());
                activityIntent.putExtras(args);
                activity.startActivity(activityIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(vehicleArrayList != null) {
            return vehicleArrayList.size();
        }
        return 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected View view;
        protected CardView card;
        protected TextView brand;
        protected TextView model;
        protected TextView year;
        protected TextView price;
        protected ImageView thumbnail;

        public CustomViewHolder(View view) {
            super(view);
            this.view = view;
            this.card = view.findViewById(R.id.card_view);
            this.brand = view.findViewById(R.id.brand);
            this.year = view.findViewById(R.id.year);
            this.model = view.findViewById(R.id.model);
            this.price = view.findViewById(R.id.price);
            this.thumbnail = view.findViewById(R.id.thumbnail);
        }

    }

}
