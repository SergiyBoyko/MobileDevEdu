package com.example.serhii.ubrainianstest.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.serhii.ubrainianstest.R;
import com.example.serhii.ubrainianstest.widgets.listeners.GyroscopeObserver;
import com.example.serhii.ubrainianstest.widgets.views.PanoramaImageView;

/**
 * Created by Serhii on 07.05.2018.
 */

public class PanoramaImageFragment extends android.support.v4.app.Fragment {

    private GyroscopeObserver gyroscopeObserver;

    public static PanoramaImageFragment newInstance() {
        return new PanoramaImageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_panorama, container, false);

        gyroscopeObserver = new GyroscopeObserver();

        PanoramaImageView panoramaImageView = (PanoramaImageView) view.findViewById(R.id.panorama_image_view);
        panoramaImageView.setGyroscopeObserver(gyroscopeObserver);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Register GyroscopeObserver.
        gyroscopeObserver.register(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        // Unregister GyroscopeObserver.
        gyroscopeObserver.unregister();
    }
}
