package com.githubissuetracker.app.roomdb;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "issues_table")
public class IssueEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "body")
    private String body;
    @ColumnInfo(name = "user")
    private String user;
    @ColumnInfo(name = "number")
    private String number;
    @ColumnInfo(name = "avatar_url")
    private String avatarUrl;
    @ColumnInfo(name = "created_at")
    private String createdAt;

    public IssueEntity(String title, String body, String user, String number, String avatarUrl,String createdAt) {
        this.title = title;
        this.body = body;
        this.user = user;
        this.number = number;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getUser() {
        return user;
    }

    public String getNumber() {
        return number;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
