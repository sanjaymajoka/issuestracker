package com.githubissuetracker.app.network;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NetworkPresenter {


    public static <T> Disposable callNetworkApi(final NetworkDataListener<T> responseListener
            , Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T list) {
                        responseListener.onSuccess(list);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        responseListener.onError(throwable);
                    }
                });

    }

}
