package com.raym.flitfilemanager.ui.filesharing;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.raym.flitfilemanager.R;
import com.raym.flitfilemanager.activities.filesharing.*;
import com.raym.flitfilemanager.databinding.FragmentFileSharingBinding;

public class FileSharingFragment extends Fragment {

    private FileSharingViewModel fileSharingViewModel;
    private FragmentFileSharingBinding binding;
    private View send, receive, invite, history, scanQR;
    private ImageView envelop;
    private AdView admobAdView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fview = inflater.inflate(R.layout.fragment_file_sharing, container, false);
        send = fview.findViewById(R.id.send);
        receive = fview.findViewById(R.id.receive);
        invite = fview.findViewById(R.id.invite);
        history = fview.findViewById(R.id.history);
        scanQR = fview.findViewById(R.id.scan_qr);
        envelop = fview.findViewById(R.id.iv_envelop);
        admobAdView = fview.findViewById(R.id.admob_adview);

        //start An Activity from a fragment
        send.setOnClickListener(view -> {
            Intent intent = new Intent (getContext(), SearchForReceiverActivity.class);
            startActivity(intent);
        });

        receive.setOnClickListener(view ->{
           Intent intent = new Intent (getContext(), ReceiveConnectionActivity.class);
           startActivity(intent);
        });

        invite.setOnClickListener(view -> {
            Intent intent = new Intent (getContext(), InviteActivity.class);
            startActivity(intent);
        });

        history.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), HistoryActivity.class);
            startActivity(intent);
        });

        scanQR.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), QRInviteActivity.class);
            startActivity(intent);
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        admobAdView.loadAd(adRequest);
        return fview;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}