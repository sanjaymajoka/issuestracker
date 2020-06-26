package com.githubissuetracker.app.viewmodels;

import android.app.Application;

import com.githubissuetracker.app.APIRepository;
import com.githubissuetracker.app.models.DataModel;
import com.githubissuetracker.app.network.NetworkDataListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class CommentsViewModel extends BaseViewModel {

    private MutableLiveData<List<DataModel>> ldList = new MutableLiveData<>();
    private MutableLiveData<Boolean> ldNoCommentAlert = new MutableLiveData<>();

    public CommentsViewModel(@NonNull Application application) {
        super(application);
    }

    public void loadComments(String number) {
        showLoader();
        APIRepository.getInstance().loadComments(number, new NetworkDataListener<List<DataModel>>() {
            @Override
            public void onSuccess(List<DataModel> data) {
                if (checkCommentsAvailable(data)) {
                    ldList.postValue(data);
                }
                hideLoader();
            }

            @Override
            public void onError(Throwable throwable) {
                handleError(throwable);
            }
        });
    }

    public MutableLiveData<List<DataModel>> getLdList() {
        return ldList;
    }

    public MutableLiveData<Boolean> getLdNoCommentAlert() {
        return ldNoCommentAlert;
    }

    private boolean checkCommentsAvailable(List<DataModel> data) {
        if (data == null || data.isEmpty()) {
            ldNoCommentAlert.postValue(true);
            return false;
        }
        return true;
    }
}
