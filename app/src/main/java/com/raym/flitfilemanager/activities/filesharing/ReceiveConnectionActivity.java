package com.raym.flitfilemanager.activities.filesharing;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.*;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import com.raym.flitfilemanager.R;
import com.raym.flitfilemanager.models.AvailableDevice;
import com.raym.flitfilemanager.receivers.ReceiveConnectionWifiDirectBroadCastReceiver;
import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReceiveConnectionActivity extends AppCompatActivity {


    public static final int FINE_LOCATION_REQUEST_CODE = 2000;
    private ArrayList<AvailableDevice> mAvailableDeviceList;

    public BroadcastReceiver mReceiver;
    public IntentFilter mIntentFilter;
    public WifiP2pManager wifiP2pManager;
    public WifiManager wifiManager;
    public WifiP2pManager.Channel channel;

    public List<WifiP2pDevice> mPeers = new ArrayList<>();
    public String[] mDeviceNameArray;
    public WifiP2pDevice[] mDeviceArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_recieve_connection);

        //Block auto opening keyboard
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        initialization();
        executeListener();

    }

    private void executeListener() {
        wifiManager.setWifiEnabled(!wifiManager.isWifiEnabled());
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ReceiveConnectionActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_REQUEST_CODE);
            return;
        }else {
            Toast.makeText(this, "permission granted already", Toast.LENGTH_SHORT).show();
        }
        wifiP2pManager.discoverPeers(channel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(ReceiveConnectionActivity.this, "Discovery Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i) {
                Toast.makeText(ReceiveConnectionActivity.this, "Discovery Starting Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initialization() {
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiP2pManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        channel = wifiP2pManager.initialize(this, getMainLooper(), null);

        mReceiver = new ReceiveConnectionWifiDirectBroadCastReceiver(wifiP2pManager, channel, this);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
    }

    public WifiP2pManager.PeerListListener peerListListener = new WifiP2pManager.PeerListListener() {
        @Override
        public void onPeersAvailable(WifiP2pDeviceList peerList) {
            if (!peerList.getDeviceList().equals(mPeers)) {
                mPeers.clear();
                mPeers.addAll(peerList.getDeviceList());
                mDeviceNameArray = new String[peerList.getDeviceList().size()];
                mDeviceArray = new WifiP2pDevice[peerList.getDeviceList().size()];

                int index = 0;
                for (WifiP2pDevice device :
                        peerList.getDeviceList()) {
                    mDeviceNameArray[index] = device.deviceName;
                    mAvailableDeviceList.add(new AvailableDevice(R.drawable.ellipse_28searcing_activity, mDeviceNameArray[index]));
                    mDeviceArray[index] = device;
                    index++;
                }
                loadRealData(mAvailableDeviceList);
            }

            if (mPeers.size() == 0) {
                Toast.makeText(ReceiveConnectionActivity.this, "No Devices Found", Toast.LENGTH_SHORT).show();
//                return;
            }
        }
    };

    public WifiP2pManager.ConnectionInfoListener connectionInfoListener = wifiP2pInfo -> {
        InetAddress groupOwnerAddress = wifiP2pInfo.groupOwnerAddress;
        if (wifiP2pInfo.groupFormed && wifiP2pInfo.isGroupOwner) {
            Toast.makeText(ReceiveConnectionActivity.this, "host", Toast.LENGTH_SHORT).show();
        } else if (wifiP2pInfo.groupFormed) {
            Toast.makeText(ReceiveConnectionActivity.this, "client", Toast.LENGTH_SHORT).show();
        }
    };

    private ArrayList<AvailableDevice> loadRealData(ArrayList<AvailableDevice> deviceArrayList) {
        mAvailableDeviceList = deviceArrayList;
        return mAvailableDeviceList;
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == FINE_LOCATION_REQUEST_CODE){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "location permission granted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}