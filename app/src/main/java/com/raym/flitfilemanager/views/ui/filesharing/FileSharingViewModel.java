package com.raym.flitfilemanager.views.ui.filesharing;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FileSharingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FileSharingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}