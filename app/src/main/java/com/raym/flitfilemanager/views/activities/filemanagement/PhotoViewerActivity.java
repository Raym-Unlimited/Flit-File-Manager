package com.raym.flitfilemanager.views.activities.filemanagement;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.raym.flitfilemanager.R;

import java.util.Objects;

public class PhotoViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_photo_viewer);
    }
}