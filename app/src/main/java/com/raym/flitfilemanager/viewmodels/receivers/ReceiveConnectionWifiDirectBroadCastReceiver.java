package com.raym.flitfilemanager.viewmodels.receivers;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import com.raym.flitfilemanager.views.activities.filesharing.ReceiveConnectionActivity;

public class ReceiveConnectionWifiDirectBroadCastReceiver extends BroadcastReceiver {

    private static final int FINE_LOCATION_REQUEST_CODE = 2000;
    private final WifiP2pManager mManager;
    private final WifiP2pManager.Channel mChannel;
    private final ReceiveConnectionActivity mReceiveConnectionActivity;

    public ReceiveConnectionWifiDirectBroadCastReceiver(WifiP2pManager mManager, WifiP2pManager.Channel mChannel, ReceiveConnectionActivity mReceiveConnectionActivity) {
        this.mManager = mManager;
        this.mChannel = mChannel;
        this.mReceiveConnectionActivity = mReceiveConnectionActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);

            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                Toast.makeText(context, "Wifi is on", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Wifi is off", Toast.LENGTH_SHORT).show();
            }
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            if (mManager != null) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(mReceiveConnectionActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_REQUEST_CODE);
//                    return;
                }else {
                    Toast.makeText(context, "permission granted already", Toast.LENGTH_SHORT).show();
                    mManager.requestPeers(mChannel, mReceiveConnectionActivity.peerListListener);
                }
            }
        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            if (mManager == null) {
                return;
            }
            NetworkInfo networkInfo = intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);
            assert networkInfo != null;
            if (networkInfo.isConnected()) {
                mManager.requestConnectionInfo(mChannel, mReceiveConnectionActivity.connectionInfoListener);
            } else {
                Toast.makeText(context, "device connected", Toast.LENGTH_SHORT).show();
            }
        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            //do something here
        }
    }
}
