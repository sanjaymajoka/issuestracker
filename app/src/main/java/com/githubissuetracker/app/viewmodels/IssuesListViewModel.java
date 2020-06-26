package com.githubissuetracker.app.viewmodels;

import android.app.Application;
import android.util.Log;

import com.githubissuetracker.app.APIRepository;
import com.githubissuetracker.app.DBRepository;
import com.githubissuetracker.app.Preferences;
import com.githubissuetracker.app.Utils;
import com.githubissuetracker.app.models.DataModel;
import com.githubissuetracker.app.network.NetworkDataListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class IssuesListViewModel extends BaseViewModel {

    private final long MAX_HOURS = 1000 * 60 * 60 * 24L;
    private MutableLiveData<List<DataModel>> ldListIssues = new MutableLiveData<>();

    public IssuesListViewModel(@NonNull Application application) {
        super(application);
    }

    public void loadIssues() {

        if (isMoreThan24hours()) {
            loadIssuesFromNetwork();
        } else {
            loadFromDb();
        }


    }

    private boolean isMoreThan24hours() {
        long lastTime = Preferences.getLastDataFetchedTime(aapContext);
        if (lastTime == 0L) {
            return true;
        }

        long currentTime = System.currentTimeMillis();

        return currentTime - lastTime > MAX_HOURS;


    }

    private void loadIssuesFromNetwork() {
        Log.e("IssueListViewModel","Loading from Network");
        if (!Utils.isNetworkConnectionAvailable(aapContext)) {
            showNetworkAlert();
            return;
        }
        showLoader();
        APIRepository.getInstance().loadIssues(new NetworkDataListener<List<DataModel>>() {
            @Override
            public void onSuccess(List<DataModel> data) {
                ldListIssues.postValue(data);
                DBRepository.getInstance().deleteAllIssues(aapContext);
                DBRepository.getInstance().saveIssues(aapContext, data);
                Preferences.saveDataFetchedTime(aapContext, System.currentTimeMillis());
                hideLoader();
            }

            @Override
            public void onError(Throwable throwable) {
                handleError(throwable);
            }
        });
    }


    private void loadFromDb() {
        Log.e("IssueListViewModel","Loading from DB");
        showLoader();
        DBRepository.getInstance().getIssueList(aapContext, new NetworkDataListener<List<DataModel>>() {
            @Override
            public void onSuccess(List<DataModel> data) {
                ldListIssues.postValue(data);
                hideLoader();
            }

            @Override
            public void onError(Throwable throwable) {
                handleError(throwable);
            }
        });
    }


    public MutableLiveData<List<DataModel>> getLdListIssues() {
        return ldListIssues;
    }
}
