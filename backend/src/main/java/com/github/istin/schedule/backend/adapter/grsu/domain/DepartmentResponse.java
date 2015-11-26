package com.github.istin.schedule.backend.adapter.grsu.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DepartmentResponse {

    @SerializedName("items")
    private List<DepartmentModel> items;

    public List<DepartmentModel> getDepartments() {
        return items;
    }
}
