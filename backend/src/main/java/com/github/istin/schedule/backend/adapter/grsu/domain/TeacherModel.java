package com.github.istin.schedule.backend.adapter.grsu.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Кривичанин on 27.11.2015.
 */
public class TeacherModel {

    @SerializedName("id")
    private String id;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("post")
    private String post;

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPost() {
        return post;
    }

    TeacherModel() {
    }

    @Override
    public String toString()
    {
        return this.fullname+" ("+this.post+")";
    }
}
