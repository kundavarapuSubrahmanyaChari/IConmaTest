package com.conma.cars.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.conma.cars.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class CarFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private CustomAdapter adapter;
    private ArrayList<Integer> images;

//    private OnFragmentInteractionListener mListener;

    public CarFragment() {
        // Required empty public constructor
    }

    public static CarFragment newInstance(String param1, String param2) {
        CarFragment fragment = new CarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.car_main_layout, container, true);

        Bundle args = getArguments();
        String brand = args.getString("brand");
        String model = args.getString("model");
        String year = args.getString("year");
        String price = args.getString("price");
        String desc = args.getString("desc");
        images = args.getIntegerArrayList("images");

        TextView brandView = view.findViewById(R.id.vehicleBrand);
        TextView modelView = view.findViewById(R.id.vehicleModel);
        TextView yearView = view.findViewById(R.id.vehicleYear);
        TextView priceView = view.findViewById(R.id.vehiclePrice);
        TextView descriptionView = view.findViewById(R.id.vehicleDesc);
        ImageView imageView = view.findViewById(R.id.imageView);

        Picasso.with(getContext()).load(images.get(0)).into(imageView);

        brandView.setText(brand);
        modelView.setText(model);
        yearView.setText(year);
        priceView.setText(price);
        descriptionView.setText(desc);

        /*adapter = new CustomAdapter(getFragmentManager());
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        CircleIndicator indicator = view.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        adapter.registerDataSetObserver(indicator.getDataSetObserver());*/

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
       /* if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
     /*   if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class CustomAdapter extends FragmentPagerAdapter {
        public CustomAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return ImagesFragment.newInstance(images.get(0));
                case 1:
                    return ImagesFragment.newInstance(images.get(1));
                case 2:
                    return ImagesFragment.newInstance(images.get(2));

                default:
                    return ImagesFragment.newInstance(0);

            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }
}