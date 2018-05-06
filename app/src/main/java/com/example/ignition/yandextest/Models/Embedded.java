package com.example.ignition.yandextest.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Embedded {


    @SerializedName("items")
    @Expose
    private List<Item> items;

    @SerializedName("path")
    @Expose
    private String path;

    @SerializedName("total")


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    @Override
    public String toString() {
        return getPath();
    }
}

