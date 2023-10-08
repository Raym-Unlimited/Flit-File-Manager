package com.raym.flitfilemanager.adapters;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.raym.flitfilemanager.R;
import com.raym.flitfilemanager.activities.filesharing.SearchForReceiverActivity;
import com.raym.flitfilemanager.dialogs.TransferBottomSheetDialog;
import com.raym.flitfilemanager.models.AvailableDevice;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AvailableDevicesRvAdapter extends RecyclerView.Adapter<AvailableDevicesRvAdapter.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<AvailableDevice> mAvailableDeviceList = new ArrayList<>();
    WifiP2pDevice mDevice;
    WifiP2pConfig mConfig;
    WifiP2pManager mManager;

    public AvailableDevicesRvAdapter(Context mContext, List<AvailableDevice> mAvailableDeviceList) {
        this.mContext = mContext;
        this.mAvailableDeviceList = mAvailableDeviceList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.rv_available_devices_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        AvailableDevice availableDevice = mAvailableDeviceList.get(position);
        holder.mUserAvatar.setBackgroundResource(availableDevice.getUserImage());
        holder.mAvailableDeviceId.setText(availableDevice.getTxtAvailableDevice());

        holder.mDeviceIdContainer.setOnClickListener(view -> {
            Toast.makeText(mContext, "Clicked me at Item " + position, Toast.LENGTH_SHORT).show();
            SearchForReceiverActivity searchForReceiverActivity = new SearchForReceiverActivity();
//            assert false;
            if (!(mAvailableDeviceList.get(position).getTxtAvailableDevice().equals("Available devices will appear here"))) {
                mDevice = searchForReceiverActivity.mDeviceArray[position];
                mConfig = new WifiP2pConfig();
                mConfig.deviceAddress = mDevice.deviceAddress;
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(mContext, Manifest.permission.NEARBY_WIFI_DEVICES) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider callingI
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mManager.connect(searchForReceiverActivity.channel, mConfig, new WifiP2pManager.ActionListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(mContext, "connected to device: " + mDevice.deviceName, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i) {
                        Toast.makeText(mContext, "not connected", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(mContext
                        , "Cannot connect to Empty Device ID", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAvailableDeviceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final View mUserAvatar;
        private final TextView mAvailableDeviceId;
        public final LinearLayout mDeviceIdContainer;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            mDeviceIdContainer = itemView.findViewById(R.id.device_id_container);
            mUserAvatar = itemView.findViewById(R.id.v_user_avatar);
            mAvailableDeviceId = itemView.findViewById(R.id.tv_available_device);

        }
    }
}
