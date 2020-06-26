package com.githubissuetracker.app.network;

public interface NetworkDataListener<T> {

    void onSuccess(T data);

    void onError(Throwable throwable);
}
