package com.githubissuetracker.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.githubissuetracker.app.models.DataModel;
import com.githubissuetracker.app.models.User;
import com.githubissuetracker.app.network.NetworkDataListener;
import com.githubissuetracker.app.roomdb.AppDatabaseClient;
import com.githubissuetracker.app.roomdb.IssueEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DBRepository {

    private static DBRepository repository;

    private DBRepository() {
    }

    public static DBRepository getInstance() {
        if (repository == null) {
            repository = new DBRepository();
        }
        return repository;
    }

    public void saveIssues(Context context, List<DataModel> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        AppDatabaseClient.getAppDataBase(context)
                .getIssueDao()
                .insert(getIssueEntityList(list)).subscribe(new Consumer<List<Long>>() {
            @Override
            public void accept(List<Long> longs) throws Exception {
                Log.e("Database", "list " + longs);
            }
        });

    }

    private List<IssueEntity> getIssueEntityList(List<DataModel> list) {
        List<IssueEntity> entityList = new ArrayList<>();
        for (DataModel dataModel : list) {
            entityList.add(new IssueEntity(dataModel.getTitle(), dataModel.getBody(), dataModel.getUser().getLogin()
                    , dataModel.getNumber(), dataModel.getUser().getAvatarUrl(), dataModel.getCreatedAt()));
        }
        return entityList;
    }

    private List<DataModel> getDataModelList(List<IssueEntity> list) {
        List<DataModel> dataModels = new ArrayList<>();
        for (IssueEntity entity : list) {
            dataModels.add(new DataModel(entity.getTitle(), entity.getBody(), entity.getNumber()
                    , entity.getCreatedAt(), new User(entity.getUser(), entity.getAvatarUrl())));
        }
        return dataModels;
    }

    @SuppressLint("CheckResult")
    public void getIssueList(Context context, final NetworkDataListener<List<DataModel>> listener) {
        AppDatabaseClient.getAppDataBase(context).getIssueDao()
                .getList().subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<IssueEntity>>() {
                    @Override
                    public void accept(List<IssueEntity> issueEntities) throws Exception {
                        listener.onSuccess(getDataModelList(issueEntities));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onError(throwable);
                    }
                });
    }

    public void deleteAllIssues(Context context) {
        AppDatabaseClient.getAppDataBase(context).getIssueDao().deleteAll();
    }
}
