package com.example.ignition.yandextest.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiskMetaDataModel {

    @SerializedName("public_key")
    @Expose
    private String publicKey;
    @SerializedName("_embedded")
    @Expose
    private Embedded embedded;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("public_url")
    @Expose
    private String publicUrl;
    @SerializedName("path")
    @Expose
    private String path;

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

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


    public String getPublicUrl() {
        return publicUrl;
    }

    public void setPublicUrl(String publicUrl) {
        this.publicUrl = publicUrl;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
