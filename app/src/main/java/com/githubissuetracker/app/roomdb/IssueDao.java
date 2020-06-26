package com.githubissuetracker.app.roomdb;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface IssueDao {
    @Insert
    Single<List<Long>> insert(List<IssueEntity> list);
    @Query("Select * from issues_table")
    Single<List<IssueEntity>> getList();
    @Query("Delete from issues_table")
    void deleteAll();
}
