package com.github.istin.schedule.backend.adapter.grsu.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Кривичанин on 27.11.2015.
 */
public class SubGroupModel {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    SubGroupModel(){}

    @Override
    public String toString()
    {
        return this.title;
    }
}
