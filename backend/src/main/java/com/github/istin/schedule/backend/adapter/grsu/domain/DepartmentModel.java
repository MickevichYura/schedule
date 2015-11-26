package com.github.istin.schedule.backend.adapter.grsu.domain;

import com.google.gson.annotations.SerializedName;

public class DepartmentModel {
    private String id;

    @SerializedName("title")
    private String title;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
