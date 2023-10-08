package com.raym.flitfilemanager.views.activities.filesharing;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.*;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.raym.flitfilemanager.R;
import com.raym.flitfilemanager.viewmodels.adapters.AvailableDevicesRvAdapter;
import com.raym.flitfilemanager.models.AvailableDevice;
import com.raym.flitfilemanager.viewmodels.receivers.WifiDirectBroadCastReceiver;
import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchForReceiverActivity extends AppCompatActivity {

    private ImageView userAvatar;
    private RecyclerView rvAvailableDevicesList;
    private View inviteReceiver, sendToPc, scanQRCode;
    private AdView mAdView;
    private Button testButton;
    private AdRequest mAdRequest;
    private LinearLayoutManager mLinearLayoutManager;
    private AvailableDevicesRvAdapter mAvailableDevicesRvAdapter = null;
    private ArrayList<AvailableDevice> mAvailableDeviceList;

    public BroadcastReceiver mReceiver;
    public IntentFilter mIntentFilter;
    public WifiP2pManager wifiP2pManager;
    public WifiManager wifiManager;
    public WifiP2pManager.Channel channel;

    public List<WifiP2pDevice> mPeers = new ArrayList<>();
    public String[] mDeviceNameArray;
    public WifiP2pDevice[] mDeviceArray;

    public static final int FINE_LOCATION_REQUEST_CODE = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_send_connection);

        initialization();
        executeListener();
        loadAdmobAdvert();
        setupRecyclerView();
        clickListeners();
    }


    private void setupRecyclerView() {

        mAvailableDeviceList = new ArrayList<>();
        mLinearLayoutManager = new LinearLayoutManager(this);

        rvAvailableDevicesList.setLayoutManager(mLinearLayoutManager);
        if (mAvailableDevicesRvAdapter == null) {
            Toast.makeText(SearchForReceiverActivity.this, "No Devices Found", Toast.LENGTH_SHORT).show();
            mAvailableDevicesRvAdapter = new AvailableDevicesRvAdapter(this, loadDummyData());
            rvAvailableDevicesList.setAdapter(mAvailableDevicesRvAdapter);
        } else {
            Toast.makeText(SearchForReceiverActivity.this, "Devices Found", Toast.LENGTH_SHORT).show();
            mAvailableDevicesRvAdapter = new AvailableDevicesRvAdapter(this, loadRealData(mAvailableDeviceList));
            rvAvailableDevicesList.setAdapter(mAvailableDevicesRvAdapter);
            mAvailableDevicesRvAdapter.notifyDataSetChanged();
        }
    }

    private ArrayList<AvailableDevice> loadDummyData() {
        mAvailableDeviceList.add(new AvailableDevice(R.drawable.ellipse_28searcing_activity, "Available devices will appear here"));
        return mAvailableDeviceList;
    }

    private ArrayList<AvailableDevice> loadRealData(ArrayList<AvailableDevice> deviceArrayList) {
        mAvailableDeviceList = deviceArrayList;
        return mAvailableDeviceList;
    }

    private void loadAdmobAdvert() {
        mAdRequest = new AdRequest.Builder().build();
        mAdView.loadAd(mAdRequest);
    }

    private void executeListener() {
        wifiManager.setWifiEnabled(!wifiManager.isWifiEnabled());
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_REQUEST_CODE);
            return;
        }else {
            Toast.makeText(this, "permission granted already", Toast.LENGTH_SHORT).show();
        }
        wifiP2pManager.discoverPeers(channel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(SearchForReceiverActivity.this, "Discovery Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i) {
                Toast.makeText(SearchForReceiverActivity.this, "Discovery Starting Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initialization() {
        //view initialization
        userAvatar = findViewById(R.id.user_avatar);
        scanQRCode = findViewById(R.id.v_scan_qr_code);
        inviteReceiver = findViewById(R.id.v_invite_receiver);
        sendToPc = findViewById(R.id.v_send_to_pc);
        rvAvailableDevicesList = findViewById(R.id.rv_available_device_list);
        mAdView = findViewById(R.id.admob_adview);
        testButton = findViewById(R.id.test_button);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiP2pManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        channel = wifiP2pManager.initialize(this, getMainLooper(), null);

        mReceiver = new WifiDirectBroadCastReceiver(wifiP2pManager, channel, this);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
    }

    private void clickListeners() {
        userAvatar.setOnClickListener(view -> Toast.makeText(SearchForReceiverActivity.this, R.string.feature_unavailable, Toast.LENGTH_SHORT).show());

        scanQRCode.setOnClickListener(view -> {
            Intent intent = new Intent(SearchForReceiverActivity.this, QRDisplayActivity.class);
            startActivity(intent);
        });

        inviteReceiver.setOnClickListener(view -> {
            Intent intent = new Intent(SearchForReceiverActivity.this, ConfigureConnectionActivity.class);
            startActivity(intent);
        });

        sendToPc.setOnClickListener(view -> {
            Intent intent = new Intent(SearchForReceiverActivity.this, SendToPCActivity.class);
            startActivity(intent);
        });

        testButton.setOnClickListener(view -> {
            Intent intent = new Intent(SearchForReceiverActivity.this, SelectItemsForTransferActivity.class);
            startActivity(intent);
        });
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
                Toast.makeText(SearchForReceiverActivity.this, "No Devices Found", Toast.LENGTH_SHORT).show();
//                return;
            }
        }
    };

    public WifiP2pManager.ConnectionInfoListener connectionInfoListener = wifiP2pInfo -> {
        InetAddress groupOwnerAddress = wifiP2pInfo.groupOwnerAddress;
        if (wifiP2pInfo.groupFormed && wifiP2pInfo.isGroupOwner) {
            Toast.makeText(SearchForReceiverActivity.this, "host", Toast.LENGTH_SHORT).show();
        } else if (wifiP2pInfo.groupFormed) {
            Toast.makeText(SearchForReceiverActivity.this, "client", Toast.LENGTH_SHORT).show();
        }
    };

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