package com.raym.flitfilemanager.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.raym.flitfilemanager.R;
import com.raym.flitfilemanager.databinding.FragmentHomeBinding;
import com.raym.flitfilemanager.fragments.CategoriesFragment;
import com.raym.flitfilemanager.fragments.RecentsFragment;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private ViewPager storageBrowserViewPager;
    private TabLayout storageBrowserTabLayout;
    private PagerAdapter pagerAdapter;
    private TabItem tabItem;

    private HomeActivityFilesAdaper homeActivityFilesAdaper;

    private ImageView sampleApp1, sampleApp2;
    private AdView adView;
    private TextView storageMonitor,txtUsedStorageNotifier;
    private ProgressBar storageProgressBar;
    long storageSize;
    String totalStorageSize, freeStorageSize;
    String totalStorageSizeOnly, freeStorageSizeOnly;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View frview = inflater.inflate(R.layout.fragment_home, container, false);

        adView = frview.findViewById(R.id.base_ads);
        sampleApp1 = frview.findViewById(R.id.sample_app_1);
        sampleApp2 = frview.findViewById(R.id.sample_app_2);
        storageBrowserTabLayout = frview.findViewById(R.id.tab_layout_home_fr);
        storageBrowserViewPager = frview.findViewById(R.id.view_pager_home_fr);
        storageMonitor = frview.findViewById(R.id.txt_storage_monitor);
        storageProgressBar = frview.findViewById(R.id.storage_monitor);
        txtUsedStorageNotifier =frview.findViewById(R.id.used_storage_txt);

        homeActivityFilesAdaper = new HomeActivityFilesAdaper(requireActivity().getSupportFragmentManager());
        homeActivityFilesAdaper.addFragment(new CategoriesFragment(), "Categories");
        homeActivityFilesAdaper.addFragment(new RecentsFragment(), "Recent");

        storageBrowserViewPager.setAdapter(homeActivityFilesAdaper);
        storageBrowserTabLayout.setupWithViewPager(storageBrowserViewPager);

        //get the internal storage directory and find the size
        File files = new File(Objects.requireNonNull(System.getenv("EXTERNAL_STORAGE")));
        //get total size
        storageSize = files.getTotalSpace();
        totalStorageSize = getHumanReadableStorageSize(storageSize);
        //get free size
        storageSize = files.getFreeSpace();
        freeStorageSize = getHumanReadableStorageSize(storageSize);
        //set the text on the storage monitor
        storageMonitor.setText(freeStorageSize +" GB" + " / " +totalStorageSize + " GB" );
//
//        int tStorage = Integer.parseInt(totalStorageSize);
//        int fStorage = Integer.parseInt(freeStorageSize);
//        int usedStorage = tStorage - fStorage;
////
//        int usedPercentage = (usedStorage/tStorage)*100;
//
        txtUsedStorageNotifier.setText(MessageFormat.format("{0}%", "87"));
//        storageProgressBar.setMax(100);
        storageProgressBar.setProgress(70);


        sampleApp1.setOnClickListener(view -> {
            Toast.makeText(getContext(), R.string.feature_unavailable, Toast.LENGTH_SHORT).show();
        });

        sampleApp2.setOnClickListener(view -> {
            Toast.makeText(getContext(), R.string.feature_unavailable, Toast.LENGTH_SHORT).show();
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        return frview;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class HomeActivityFilesAdaper extends FragmentPagerAdapter {

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        ArrayList<String> stringArrayList   = new ArrayList<>();

        public void addFragment (Fragment fragment, String s){
            fragmentArrayList.add(fragment);
            stringArrayList.add(s);
        }
        public HomeActivityFilesAdaper(@NonNull @NotNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @NotNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        @Nullable
        @org.jetbrains.annotations.Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return stringArrayList.get(position);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
        homeActivityFilesAdaper = new HomeActivityFilesAdaper(requireActivity().getSupportFragmentManager());
        homeActivityFilesAdaper.addFragment(new CategoriesFragment(), "Categories");
        homeActivityFilesAdaper.addFragment(new RecentsFragment(), "Recent");
        storageBrowserViewPager.setAdapter(homeActivityFilesAdaper);
        storageBrowserTabLayout.setupWithViewPager(storageBrowserViewPager);
    }

    private String getHumanReadableStorageSize(long storageSize) {

        String humanReadableSize = null;

        if (storageSize < 1024){
            humanReadableSize = String.format(this
                    .getString(R.string.app_size_bytes), (double) storageSize);
        }else if (storageSize < Math.pow(1024, 2)){
            humanReadableSize = String.format(this
                    .getString(R.string.app_size_kbytes), (double) storageSize/1024);
        }else if (storageSize < Math.pow(1024, 3)){
            humanReadableSize = String.format(this
                    .getString(R.string.app_size_mbytes), (double) storageSize/Math.pow(1024, 2));
        }else if (storageSize < Math.pow(1024, 4)){
            humanReadableSize = String.format(this
                    .getString(R.string.app_size_only), (double) storageSize/Math.pow(1024, 3));
        }
        return humanReadableSize;
    }
}