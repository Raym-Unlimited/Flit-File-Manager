package com.raym.flitfilemanager.activities.filesharing;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.raym.flitfilemanager.R;

import java.util.Objects;

public class PostTransferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_transfer);
        Objects.requireNonNull(getSupportActionBar()).hide();

    }
}