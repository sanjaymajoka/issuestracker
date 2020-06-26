package com.githubissuetracker.app.viewmodels;

import android.app.Application;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class SplashViewModel extends BaseViewModel {

    private MutableLiveData<Boolean> ldOpenHome = new MutableLiveData<>();

    public SplashViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Boolean> getLdOpenHome() {
        return ldOpenHome;
    }

    public void handleSplash(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ldOpenHome.postValue(null);
            }
        },1000L);
    }
}
