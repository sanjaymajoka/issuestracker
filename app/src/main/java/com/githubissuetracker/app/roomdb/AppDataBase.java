package com.githubissuetracker.app.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {IssueEntity.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract IssueDao getIssueDao();
}
