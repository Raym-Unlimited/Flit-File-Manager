package com.raym.flitfilemanager.views.activities.devicecare;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.raym.flitfilemanager.R;

import java.util.Objects;

public class CPUCoolerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_cpucooler);
    }
}