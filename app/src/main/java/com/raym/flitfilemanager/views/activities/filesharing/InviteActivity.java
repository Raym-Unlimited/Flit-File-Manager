package com.raym.flitfilemanager.views.activities.filesharing;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.raym.flitfilemanager.R;

import java.util.Objects;

public class InviteActivity extends AppCompatActivity {

    private View inviteViaWhatsApp;
    private View inviteViaQR;
    private View inviteViaMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        Objects.requireNonNull(getSupportActionBar()).hide();

        inviteViaWhatsApp = findViewById(R.id.btn_wa_invite);
        inviteViaQR = findViewById(R.id.btn_qr_invite);
        inviteViaMore = findViewById(R.id.btn_more_invite);

        inviteViaWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareAppLink();
            }
        });

        inviteViaQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InviteActivity.this, QRInviteActivity.class);
                startActivity(intent);
            }
        });

        inviteViaMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareAppLink();
            }
        });
    }

    private void shareAppLink() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        //mime type
        sharingIntent.setType("text/plain");
        String shareBody = "Hey, I use monk File Security and File Manager to share my important files," +
                "and keep my phone healthy try it out at https://play.google.com/monkfilemanager";
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Download Monk Security and File Manager");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody );
        startActivity(Intent.createChooser(sharingIntent, "Invite People to Monk Security File Manager"));
    }
}