package com.example.ignition.yandextest.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiskMetaDataModel {

    @SerializedName("_embedded")
    @Expose
    private Embedded embedded;
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("public_url")


    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
