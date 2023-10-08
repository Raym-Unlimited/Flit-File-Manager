package com.raym.flitfilemanager.views.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.raym.flitfilemanager.R;
import com.raym.flitfilemanager.viewmodels.adapters.VideoRecyclerAdapter;


public class VideosFragment extends Fragment {

//    ArrayList<Vid> mVids = new ArrayList<>();

    public VideosFragment() {
        // Required empty public constructor 
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View frView = inflater.inflate(R.layout.fragment_videos, container, false);

        RecyclerView recyclerView = frView.findViewById(R.id.rv_videos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        VideoRecyclerAdapter videoRecyclerAdapter = new VideoRecyclerAdapter(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(videoRecyclerAdapter);

        return frView;
    }
}