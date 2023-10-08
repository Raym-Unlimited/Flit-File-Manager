package com.raym.flitfilemanager.views.fragments;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.raym.flitfilemanager.R;
import com.raym.flitfilemanager.viewmodels.adapters.AudioRecyclerAdapter;


@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class AudiosFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private AudioRecyclerAdapter mAudioRecyclerAdapter;
    String[] items;

    public AudiosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View frView = inflater.inflate(R.layout.fragment_audios, container, false);
        //setup RecyclerView
        mRecyclerView = frView.findViewById(R.id.rv_audios);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAudioRecyclerAdapter = new AudioRecyclerAdapter(getContext());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAudioRecyclerAdapter);
        return frView;
    }
    
    @Override
    public void onResume() {
        super.onResume();
//        mAudioRecyclerAdapter.notifyDataSetChanged();
    }
}