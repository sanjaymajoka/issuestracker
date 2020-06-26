package com.githubissuetracker.app;

import com.githubissuetracker.app.models.DataModel;
import com.githubissuetracker.app.network.ApiClient;
import com.githubissuetracker.app.network.ApiInterface;
import com.githubissuetracker.app.network.NetworkDataListener;
import com.githubissuetracker.app.network.NetworkPresenter;

import java.util.List;

public class APIRepository {

    private static APIRepository repository;

    private APIRepository() {
    }

    public static APIRepository getInstance() {
        if (repository == null) {
            repository = new APIRepository();
        }
        return repository;
    }

    public void loadIssues(NetworkDataListener<List<DataModel>> dataListener) {
        NetworkPresenter.callNetworkApi(dataListener, ApiClient.createApi(ApiInterface.class).getIssues());
    }

    public void loadComments(String number, NetworkDataListener<List<DataModel>> dataListener) {
        NetworkPresenter.callNetworkApi(dataListener, ApiClient.createApi(ApiInterface.class).getComments(number));
    }

}
