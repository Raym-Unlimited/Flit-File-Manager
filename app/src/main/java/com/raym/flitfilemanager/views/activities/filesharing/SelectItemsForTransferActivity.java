package com.raym.flitfilemanager.views.activities.filesharing;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.raym.flitfilemanager.R;
import com.raym.flitfilemanager.views.fragments.ApksFragment;
import com.raym.flitfilemanager.views.fragments.AudiosFragment;
import com.raym.flitfilemanager.views.fragments.FoldersFragment;
import com.raym.flitfilemanager.views.fragments.HistoryFragment;
import com.raym.flitfilemanager.views.fragments.ImagesFragment;
import com.raym.flitfilemanager.views.fragments.VideosFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class SelectItemsForTransferActivity extends AppCompatActivity {

    private final int STORAGE_PERMISSION_CODE = 1;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    SelectItemsForTransferAdapter selectItemsForTransferAdapter;
    private Button sendButton;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_select_items_for_transfer);
        tabLayout = findViewById(R.id.tabLayoutTransferActivity);
        viewPager = findViewById(R.id.transferViewPager);


        selectItemsForTransferAdapter = new SelectItemsForTransferAdapter(getSupportFragmentManager());
        selectItemsForTransferAdapter.addFragment(new HistoryFragment(), "History");
        selectItemsForTransferAdapter.addFragment(new FoldersFragment(), "Folders");
        selectItemsForTransferAdapter.addFragment(new VideosFragment(), "Videos");
        selectItemsForTransferAdapter.addFragment(new ImagesFragment(), "Images");
        selectItemsForTransferAdapter.addFragment(new ApksFragment(), "Apps");
        selectItemsForTransferAdapter.addFragment(new AudiosFragment(), "Audios");

        viewPager.setAdapter(selectItemsForTransferAdapter);
        tabLayout.setupWithViewPager(viewPager);
        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.grouphistoryicon);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.layer_7transfericons);
        Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(R.drawable.vectortransfericons);
        Objects.requireNonNull(tabLayout.getTabAt(3)).setIcon(R.drawable.layer_10transfericons);
        Objects.requireNonNull(tabLayout.getTabAt(4)).setIcon(R.drawable.grouptransfericons);
        Objects.requireNonNull(tabLayout.getTabAt(5)).setIcon(R.drawable.group_12transfericons);
    }

    private class SelectItemsForTransferAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();

        public void addFragment(Fragment fragment, String s) {
            fragmentArrayList.add(fragment);
            stringArrayList.add(s);
        }

        public SelectItemsForTransferAdapter(@NonNull @NotNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @NotNull
        @Override
        public Fragment getItem(int position) {
            Fragment currentFragment = fragmentArrayList.get(position);
            if (currentFragment.equals(new HistoryFragment())) {
                sendButton.setVisibility(View.INVISIBLE);
            }
            return currentFragment;
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

//        @Nullable
//        @org.jetbrains.annotations.Nullable
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return stringArrayList.get(position);
//        }
    }
}