package com.raym.flitfilemanager.models;


import java.util.ArrayList;

public class AvailableDevice {

    int userImage;
    String txtAvailableDevice;

    ArrayList<AvailableDevice> availableDevices = new ArrayList<>();

    public AvailableDevice(){
    }

    public AvailableDevice(int userImage, String txtAvailableDevice) {
        this.userImage = userImage;
        this.txtAvailableDevice = txtAvailableDevice;
    }

    public int getUserImage() {
        return userImage;
    }

    public void setUserImage(int userImage) {
        this.userImage = userImage;
    }

    public String getTxtAvailableDevice() {
        return txtAvailableDevice;
    }

    public void setTxtAvailableDevice(String txtAvailableDevice) {
        this.txtAvailableDevice = txtAvailableDevice;
    }
}
