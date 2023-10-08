package com.raym.flitfilemanager.views.fragments;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import com.karumi.dexter.Dexter;
//import com.karumi.dexter.MultiplePermissionsReport;
//import com.karumi.dexter.PermissionToken;
//import com.karumi.dexter.listener.PermissionRequest;
//import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.raym.flitfilemanager.R;
import com.raym.flitfilemanager.viewmodels.adapters.AppsRecyclerAdapter;
import com.raym.flitfilemanager.models.App;
import com.raym.flitfilemanager.models.EndlessRecyclerViewScrollListener;

import java.io.File;
import java.util.*;

public class ApksFragment extends Fragment {

    GridLayoutManager gridLayoutManager;
    AppsRecyclerAdapter appsRecyclerAdapter;
    ArrayList<App> appArrayList = new ArrayList<>();
    Button sendButton;
    private RecyclerView mRecyclerView;
    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;

    public ApksFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View frView = inflater.inflate(R.layout.fragment_app, container, false);
        sendButton = frView.findViewById(R.id.btn_send);
        mRecyclerView = frView.findViewById(R.id.rv_app_list);

        gridLayoutManager = new GridLayoutManager(getContext(), 5, RecyclerView.VERTICAL,
                false);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

            }
        };
        PackageManager pm = requireContext().getApplicationContext().getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo applicationInfo : packages) {
            String name;
            if ((name = String.valueOf(pm.getApplicationLabel(applicationInfo))).isEmpty()) {
                name = applicationInfo.packageName;
            }

            Drawable icon = pm.getApplicationIcon(applicationInfo);
            String apkPath = applicationInfo.sourceDir;
            long apkSize = new File(applicationInfo.sourceDir).length();

            appArrayList.add(new App(icon, name, apkPath, apkSize));
        }

        appArrayList.sort(new Comparator<App>() {
            @Override
            public int compare(App app, App t1) {
                return app.getAppName().toLowerCase().compareTo(t1.getAppName().toLowerCase());
            }
        });

        appsRecyclerAdapter = new AppsRecyclerAdapter(getContext(), appArrayList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(appsRecyclerAdapter);
        mRecyclerView.setItemViewCacheSize(10);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
//        appsRecyclerAdapter.notifyDataSetChanged();

        return frView;
    }

    @Override
    public void onResume() {
        super.onResume();
        appsRecyclerAdapter = new AppsRecyclerAdapter(getContext(), appArrayList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(appsRecyclerAdapter);
//        appsRecyclerAdapter.notifyDataSetChanged();

    }
}