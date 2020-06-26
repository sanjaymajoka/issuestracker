package com.githubissuetracker.app.viewmodels;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public abstract class BaseViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> ldLoader = new MutableLiveData<>();
    private MutableLiveData<String> ldToastMessage = new MutableLiveData<>();
    private MutableLiveData<Boolean> ldNetworkDialog = new MutableLiveData<>();
    Context aapContext;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        aapContext = application.getApplicationContext();
    }

    void handleError(Throwable throwable) {
        Log.e("BaseViewMode", "Error : " + throwable, throwable);
    }

    public MutableLiveData<Boolean> getLdLoader() {
        return ldLoader;
    }

    public MutableLiveData<String> getLdToastMessage() {
        return ldToastMessage;
    }

    void showLoader() {
        ldLoader.postValue(true);
    }

    void hideLoader() {
        ldLoader.postValue(false);
    }

    public MutableLiveData<Boolean> getLdNetworkDialog() {
        return ldNetworkDialog;
    }

    void showToast(String message) {
        ldToastMessage.postValue(message);
    }

    void showNetworkAlert() {
        ldNetworkDialog.postValue(true);
    }
}
