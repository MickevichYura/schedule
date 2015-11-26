package com.github.istin.schedule.backend.adapter.grsu.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FacultyResponse {

    @SerializedName("items")
    private List<FacultyModel> items;

    public List<FacultyModel> getFaculties() {
        return items;
    }
}
