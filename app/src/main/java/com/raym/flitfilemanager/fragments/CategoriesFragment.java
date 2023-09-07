package com.raym.flitfilemanager.fragments;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.raym.flitfilemanager.R;


public class CategoriesFragment extends Fragment {

    private ImageButton actionPhotos, actionVideos, actionDocuments,
            actionZip, actionAPK, actionAudios, actionDownloads, actionMore;

    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View frview = inflater.inflate(R.layout.fragment_categories, container, false);
        actionPhotos = frview.findViewById(R.id.action_1);
        actionVideos = frview.findViewById(R.id.action_2);
        actionDocuments = frview.findViewById(R.id.action_3);
        actionZip = frview.findViewById(R.id.action_4);
        actionAPK = frview.findViewById(R.id.action_5);
        actionAudios = frview.findViewById(R.id.action_6);
        actionDownloads = frview.findViewById(R.id.action_7);
        actionMore= frview.findViewById(R.id.action_8);


        actionPhotos.setOnClickListener(view -> {
            Toast.makeText(getContext(), R.string.feature_unavailable, Toast.LENGTH_SHORT).show();
        });

        actionVideos.setOnClickListener(view -> {
            Toast.makeText(getContext(), R.string.feature_unavailable, Toast.LENGTH_SHORT).show();
        });

        actionDocuments.setOnClickListener(view -> {
            Toast.makeText(getContext(), R.string.feature_unavailable, Toast.LENGTH_SHORT).show();
        });

        actionZip.setOnClickListener(view -> {
            Toast.makeText(getContext(), R.string.feature_unavailable, Toast.LENGTH_SHORT).show();
        });

        actionAPK.setOnClickListener(view -> {
            Toast.makeText(getContext(), R.string.feature_unavailable, Toast.LENGTH_SHORT).show();
        });

        actionAudios.setOnClickListener(view -> {
            Toast.makeText(getContext(), R.string.feature_unavailable, Toast.LENGTH_SHORT).show();
        });

        actionDownloads.setOnClickListener(view -> {
            Toast.makeText(getContext(), R.string.feature_unavailable, Toast.LENGTH_SHORT).show();
        });

        actionMore.setOnClickListener(view -> {
            Toast.makeText(getContext(), R.string.feature_unavailable, Toast.LENGTH_SHORT).show();
        });

        return frview;
    }
}