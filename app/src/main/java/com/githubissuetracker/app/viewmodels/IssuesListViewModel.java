package com.githubissuetracker.app.viewmodels;

import android.app.Application;

import com.githubissuetracker.app.APIRepository;
import com.githubissuetracker.app.DBRepository;
import com.githubissuetracker.app.models.DataModel;
import com.githubissuetracker.app.network.NetworkDataListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class IssuesListViewModel extends BaseViewModel {

    private MutableLiveData<List<DataModel>> ldListIssues = new MutableLiveData<>();

    public IssuesListViewModel(@NonNull Application application) {
        super(application);
    }

    public void loadIssues() {
        showLoader();
        APIRepository.getInstance().loadIssues(new NetworkDataListener<List<DataModel>>() {
            @Override
            public void onSuccess(List<DataModel> data) {
                ldListIssues.postValue(data);
                DBRepository.getInstance().deleteAllIssues(aapContext);
                DBRepository.getInstance().saveIssues(aapContext, data);
                hideLoader();
            }

            @Override
            public void onError(Throwable throwable) {
                handleError(throwable);
            }
        });
    }


    public void loadFromDb() {
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
