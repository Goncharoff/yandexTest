package com.example.ignition.yandextest.PicturesList;

import android.content.Context;

import java.util.ArrayList;

public class Presenter implements GetMetaDataContract.Presenter, GetMetaDataContract.OnGetDataListener, GetMetaDataContract.View {
    private GetMetaDataContract.View mGetDataView;
    private RetrofitInteracted mRetrofitInteracted;

    Presenter(GetMetaDataContract.View mGetDataView) {
        this.mGetDataView = mGetDataView;
        mRetrofitInteracted = new RetrofitInteracted(this);
    }

    @Override
    public void onGetUrlSuccess(String message, ArrayList<String> imagesUrlList) {
        mGetDataView.onGetUrlSuccess(message, imagesUrlList);
    }

    @Override
    public void onGetDataFailure(String message) {
        mGetDataView.onGetDataFailure(message);
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
