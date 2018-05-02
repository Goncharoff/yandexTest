package com.example.ignition.yandextest.PicturesList;

import android.content.Context;

import com.example.ignition.yandextest.Models.DiskMetaDataModel;

import java.util.List;

public interface GetMetaDataContract {
    interface onGetDataListener {
        void onSuccess(String message, DiskMetaDataModel diskMetaDataModel);

        void onGetUrlSuccess(String message, List<String> imagesUrlList);

        void onFailure(String message);
    }

    interface RetrofitInteracted {
        void initRetrofitCall(Context context, String url);
    }

    interface Presenter {
        void getDataFromURL(Context context, String url);
    }

    interface View {
        void onGetDataSuccess(String message, DiskMetaDataModel diskMetaDataModel);

        void onGetUrlSuccess(String message, List<String> imagesUrlList);

        void onGetDataFailure(String message);
    }
}
