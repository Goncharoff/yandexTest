package com.example.ignition.yandextest.ImageInfoActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ignition.yandextest.Models.DiskMetaDataModel;
import com.example.ignition.yandextest.Models.Item;
import com.example.ignition.yandextest.Models.MetaDataResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitImageInfoModel implements GetImageInfoContract.RetrofitInteracted {
    private GetImageInfoContract.OnGetDataListener mOnGetDataListener;

    private List<Item> imageInfoData = new ArrayList<>();


    RetrofitImageInfoModel(GetImageInfoContract.OnGetDataListener mOnGetDataListener) {
        this.mOnGetDataListener = mOnGetDataListener;
    }

    @Override
    public void initRetrofitCall(Context context, String url) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cloud-api.yandex.net/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MetaDataResponse metaDataRequest = retrofit.create(MetaDataResponse.class);
        Call<DiskMetaDataModel> call = metaDataRequest.getDiskMetaData("https://yadi.sk/d/oTYeRSSR3UToKa");

        call.enqueue(new Callback<DiskMetaDataModel>() {
            @Override
            public void onResponse(@NonNull Call<DiskMetaDataModel> call, @NonNull Response<DiskMetaDataModel> response) {
                DiskMetaDataModel diskMetaDataModel = response.body();
                imageInfoData = diskMetaDataModel.getEmbedded().getItems();

                Log.d("Data", "Refreshed");

                mOnGetDataListener.onGetUrlSuccessImageInfo("Success", imageInfoData);

            }

            @Override
            public void onFailure(@NonNull Call<DiskMetaDataModel> call, Throwable t) {
                Log.d("Error", t.getMessage());
                mOnGetDataListener.onFailure(t.getMessage());
            }
        });
    }
}
