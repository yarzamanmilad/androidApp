package com.example.khialerahat.experts_khialerahat.ApiModel;

import com.google.gson.annotations.SerializedName;

public class Model_ostan
{
@SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
