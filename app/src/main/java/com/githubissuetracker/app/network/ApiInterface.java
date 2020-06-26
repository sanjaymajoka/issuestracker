package com.githubissuetracker.app.network;


import com.githubissuetracker.app.models.DataModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET(NetworkUrl.ISSUES_URL)
    Observable<List<DataModel>> getIssues();

    @GET(NetworkUrl.COMMENTS_URL)
    Observable<List<DataModel>> getComments(@Path(value = "number") String number);
}
