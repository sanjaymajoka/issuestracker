package com.githubissuetracker.app.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DataModel implements Parcelable {

    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;
    /*@SerializedName("comments_url")
    private String commentUrl;*/
    @SerializedName("number")
    private String number;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("user")
    private User user;

    public DataModel(String title, String body, String number, String createdAt,User user) {
        this.title = title;
        this.body = body;
        this.number = number;
        this.user = user;
        this.createdAt = createdAt;
    }

    protected DataModel(Parcel in) {
        title = in.readString();
        body = in.readString();
        number = in.readString();
        createdAt = in.readString();
        user = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<DataModel> CREATOR = new Creator<DataModel>() {
        @Override
        public DataModel createFromParcel(Parcel in) {
            return new DataModel(in);
        }

        @Override
        public DataModel[] newArray(int size) {
            return new DataModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

   /* public String getCommentUrl() {
        return commentUrl;
    }*/

    public String getNumber() {
        return number;
    }

    public User getUser() {
        return user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(body);
        dest.writeString(number);
        dest.writeString(createdAt);
        dest.writeParcelable(user, flags);
    }
}
