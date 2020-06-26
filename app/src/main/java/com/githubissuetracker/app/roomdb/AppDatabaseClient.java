package com.githubissuetracker.app.roomdb;

import android.content.Context;

import androidx.room.Room;

public class AppDatabaseClient {

    private static AppDataBase appDataBase;

    private AppDatabaseClient(){}

    public static synchronized AppDataBase getAppDataBase(Context context){
        if(appDataBase == null){
            appDataBase = Room.databaseBuilder(context.getApplicationContext()
                    , AppDataBase.class,AppDataBase.class.getSimpleName())
                    .build();
        }
        return appDataBase;
    }

}
