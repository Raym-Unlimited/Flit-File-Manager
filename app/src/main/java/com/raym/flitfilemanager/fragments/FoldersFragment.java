package com.raym.flitfilemanager.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.raym.flitfilemanager.R;
import com.raym.flitfilemanager.adapters.FolderRecyclerAdapter;

import java.util.ArrayList;

public class FoldersFragment extends Fragment {

    public FoldersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View frView = inflater.inflate(R.layout.fragment_folders, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView recyclerView = frView.findViewById(R.id.rv_folders);
        recyclerView.setLayoutManager(linearLayoutManager);
//        for (int i = 0; folderArrayList.size()<=10; i++){
//            folderArrayList.add(new Folder("Android", "3 | 12-May-2021"));
//        }

        FolderRecyclerAdapter folderRecyclerAdapter = new FolderRecyclerAdapter(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(folderRecyclerAdapter);
//        folderRecyclerAdapter.notifyDataSetChanged();
        return frView;
    }
}