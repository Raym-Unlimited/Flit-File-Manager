package com.raym.flitfilemanager.ui.toolkit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ToolkitViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ToolkitViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}