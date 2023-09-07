package com.raym.flitfilemanager.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import com.raym.flitfilemanager.HomeActivity;
import com.raym.flitfilemanager.R;
import com.raym.flitfilemanager.models.Method;
import com.raym.flitfilemanager.models.StorageUtil;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Objects;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView monkFolder, horizontalBar;
    TextView monkBold, monkSmall;
    ProgressBar pbLoading;
    private File storage;
    private String[] allPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Objects.requireNonNull(getSupportActionBar()).hide();

        monkFolder = findViewById(R.id.imageView_splash);
        horizontalBar = findViewById(R.id.imageView2_splash);
        monkBold = findViewById(R.id.textView_splash);
        monkSmall = findViewById(R.id.textView2_splash);
        pbLoading = findViewById(R.id.pb_splash);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.enter_from_left);
        animation.setDuration(500);

        monkFolder.startAnimation(animation);
        horizontalBar.startAnimation(animation);
        monkBold.startAnimation(animation);
        monkSmall.startAnimation(animation);
        pbLoading.startAnimation(animation);
        
        new Handler().postDelayed(this::gotoHomeActivity, 5000);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkPermission();
    }

    @Override
    protected void onPause() {
        super.onPause();
        checkPermission();
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(this), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Objects.requireNonNull(this), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(this, "This is required to function properly", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "permission granted already", Toast.LENGTH_SHORT).show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    allPath = StorageUtil.getStorageDirectories(SplashScreenActivity.this);
                    for (String path : allPath){
                        storage = new File(path);
                        Method.load_Directory_Files(storage);
                    }
                }
            }).start();
        }
    }

    //move to splash screen
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "storage permission granted", Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        allPath = StorageUtil.getStorageDirectories(SplashScreenActivity.this);
                        for (String path : allPath){
                            storage = new File(path);
                            Method.load_Directory_Files(storage);
                        }
                    }
                }).start();
            }else{
                Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void gotoWelcomeActivity(){
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
    }

    private void gotoHomeActivity(){
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}