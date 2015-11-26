package com.github.istin.schedule.backend.adapter.grsu.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GroupResponse {

    @SerializedName("items")
    private List<GroupModel> items;

    public List<GroupModel> getGroups() {
        return items;
    }
}
