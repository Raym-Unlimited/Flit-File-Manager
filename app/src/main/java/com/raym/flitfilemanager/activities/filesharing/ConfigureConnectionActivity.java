package com.raym.flitfilemanager.activities.filesharing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.raym.flitfilemanager.R;


import java.util.Objects;

public class ConfigureConnectionActivity extends AppCompatActivity {

    private View nextButton;
    private LinearLayout step2;
    private TextView tvViewStep2;

    WifiP2pManager manager;
    WifiP2pManager.Channel channel;
    BroadcastReceiver receiver;
    IntentFilter intentFilter;

    public static final String TAG = "Monk File Transfer: ";
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_FINE_LOCATION = 1001;
    private boolean isWifiP2pEnabled = false;
    private boolean retryChannel = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_configure_connection);

        nextButton = findViewById(R.id.v_next_button);
        step2 =findViewById(R.id.fr_step_2);
        tvViewStep2 = findViewById(R.id.tv_view_step_2);

        //when the user clicks on next
        nextButton.setOnClickListener(view -> {
            step2.setVisibility(View.VISIBLE);
            nextButton.setVisibility(View.GONE);
            tvViewStep2.setVisibility(View.GONE);
        });
    }
}