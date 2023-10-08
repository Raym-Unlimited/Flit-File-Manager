package com.raym.flitfilemanager.views.activities.filesharing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.raym.flitfilemanager.R;
import com.raym.flitfilemanager.views.fragments.ReceivedFragment;
import com.raym.flitfilemanager.views.fragments.SentFragment;
import com.raym.flitfilemanager.views.fragments.ReceivedFragment;
import com.raym.flitfilemanager.views.fragments.SentFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class HistoryActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    HistoryAdapter historyAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_history);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.v_p);

        historyAdapter = new HistoryAdapter(getSupportFragmentManager());

        historyAdapter.addFragment(new SentFragment(), "Sent Files");
        historyAdapter.addFragment(new ReceivedFragment(), "Received Files");

        viewPager.setAdapter(historyAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    private class HistoryAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();

        public void addFragment(Fragment fragment, String s){
            fragmentArrayList.add(fragment);
            stringArrayList.add(s);
        }

        public HistoryAdapter(@NonNull @NotNull FragmentManager fm) {
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
}