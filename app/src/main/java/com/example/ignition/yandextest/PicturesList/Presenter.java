package com.example.ignition.yandextest.PicturesList;

import android.content.Context;

import com.example.ignition.yandextest.Models.DiskMetaDataModel;

import java.util.List;

public class Presenter implements GetMetaDataContract.Presenter, GetMetaDataContract.onGetDataListener {
    private GetMetaDataContract.View mGetDataView;
    private RetrofitInteracted mRetrofitInteracted;

    Presenter(GetMetaDataContract.View mGetDataView) {
        this.mGetDataView = mGetDataView;
        mRetrofitInteracted = new RetrofitInteracted(this);
    }

    @Override
    public void onSuccess(String message, DiskMetaDataModel diskMetaDataModel) {
        mGetDataView.onGetDataSuccess(message, diskMetaDataModel);
    }

    @Override
    public void onGetUrlSuccess(String message, List<String> imagesUrlList) {
        mGetDataView.onGetUrlSuccess(message, imagesUrlList);
    }

    @Override
    public void onFailure(String message) {
        mGetDataView.onGetDataFailure(message);
    }

    @Override
    public void getDataFromURL(Context context, String url) {
        mRetrofitInteracted.initRetrofitCall(context, url);
    }
}
