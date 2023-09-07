package com.raym.flitfilemanager.models;

import android.graphics.drawable.Drawable;

public class App {


    private String appName = "Gmail";
    private String apkPath = "20.77 GB";
    private Drawable appIcon;
    private long appSize;

    public App(){
    }

    public App(Drawable appIcon, String appName, String apkPath, long appSize) {
        this.appIcon = appIcon;
        this.appName = appName;
        this.apkPath = apkPath;
        this.appSize = appSize;
    }

    public long getAppSize() {
        return appSize;
    }

    public void setAppSize(long appSize) {
        this.appSize = appSize;
    }


    public Drawable getAppImage() {
        return appIcon;
    }

    public void setAppImage(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getApkPath() {
        return apkPath;
    }

    public void setApkPath(String apkPath) {
        this.apkPath = apkPath;
    }
}
