package com.raym.flitfilemanager.views.ui.toolkit;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.raym.flitfilemanager.R;
import com.raym.flitfilemanager.views.activities.devicecare.BatterySaverActivity;
import com.raym.flitfilemanager.views.activities.devicecare.CPUCoolerActivity;
import com.raym.flitfilemanager.views.activities.devicecare.JunkCleanerActivity;
import com.raym.flitfilemanager.views.activities.devicecare.PhoneBoostActivity;
import com.raym.flitfilemanager.databinding.FragmentToolkitBinding;

public class ToolkitFragment extends Fragment {

    private ToolkitViewModel toolkitViewModel;
    private FragmentToolkitBinding binding;
    private ImageView junkClean,phoneBoost, batterySaver, cpuCooler;
    private AdView adMobAdView;
    private TextView adTextView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View frView = inflater.inflate(R.layout.fragment_toolkit, container, false);

        junkClean = frView.findViewById(R.id.base_junk_clean);
        phoneBoost = frView.findViewById(R.id.base_phone_boost);
        batterySaver = frView.findViewById(R.id.base_battery_saver);
        cpuCooler = frView.findViewById(R.id.base_cpu_cooler);
        adMobAdView = frView.findViewById(R.id.base_ad);
        adTextView = frView.findViewById(R.id.ads_text);

        junkClean.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), JunkCleanerActivity.class);
            startActivity(intent);
        });

        phoneBoost.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), PhoneBoostActivity.class);
            startActivity(intent);
        });

        batterySaver.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), BatterySaverActivity.class);
            startActivity(intent);
        });

        cpuCooler.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), CPUCoolerActivity.class);
            startActivity(intent);
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        adMobAdView.loadAd(adRequest);

        adTextView.setVisibility(View.INVISIBLE);

        return frView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}