package com.raym.flitfilemanager.views.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.raym.flitfilemanager.R;
import com.raym.flitfilemanager.viewmodels.adapters.ImagesRecyclerAdapter;


public class ImagesFragment extends Fragment {

    private ImagesRecyclerAdapter mImagesRecyclerAdapter;
    private GridLayoutManager mGridLayoutManager;

    public ImagesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View frView = inflater.inflate(R.layout.fragment_images, container, false);
        RecyclerView recyclerView = frView.findViewById(R.id.rv_images);
        mGridLayoutManager = new GridLayoutManager(getContext(), 4, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(mGridLayoutManager);

        mImagesRecyclerAdapter = new ImagesRecyclerAdapter(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(mImagesRecyclerAdapter);
        return frView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mImagesRecyclerAdapter.notifyDataSetChanged();
    }
}