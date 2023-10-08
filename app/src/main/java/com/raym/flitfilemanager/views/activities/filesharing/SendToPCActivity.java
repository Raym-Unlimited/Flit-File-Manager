package com.raym.flitfilemanager.views.activities.filesharing;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.raym.flitfilemanager.R;

import java.util.Objects;

public class SendToPCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_send_to_pcactivity);
    }
}