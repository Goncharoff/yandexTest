package com.example.ignition.yandextest.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Embedded {

    @SerializedName("public_key")
    @Expose
    private String publicKey;

    @SerializedName("items")
    @Expose
    private List<Item> items;

    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("total")
    @Expose
    private Integer total;


    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return getPath();
    }
}

