package com.raym.flitfilemanager.models;

import java.util.ArrayList;
import java.util.List;

public class DataBank {

    AvailableDevice availableDevice;
    List<AvailableDevice> availableDevices = new ArrayList<>();


    public List<AvailableDevice> getAvailableDevices() {
        return availableDevices;
    }

    public void setAvailableDevices(List<AvailableDevice> availableDevices) {
        this.availableDevices = availableDevices;
    }

    public AvailableDevice getAvailableDevice() {
        return availableDevice;
    }

    public void setAvailableDevice(AvailableDevice availableDevice) {
        this.availableDevice = availableDevice;
    }
}
