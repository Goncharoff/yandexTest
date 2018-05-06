package com.example.ignition.yandextest.Models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MetaDataResponse {
    @GET("disk/public/resources?")
    Call<DiskMetaDataModel> getDiskMetaData(@Query("public_key") String publicKey);
}