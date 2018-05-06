package com.example.ignition.yandextest.ImageInfoActivity;

import android.content.Context;

import com.example.ignition.yandextest.Models.Item;
import java.util.List;

public class ImageInfoPresenter implements GetImageInfoContract.Presenter, GetImageInfoContract.ViewImageInfo, GetImageInfoContract.OnGetDataListener{
    private GetImageInfoContract.ViewImageInfo mGetDataView;
    private RetrofitImageInfoModel mRetrofitImageInfoModel;

    ImageInfoPresenter(GetImageInfoContract.ViewImageInfo mGetDataView) {
        this.mGetDataView = mGetDataView;
        mRetrofitImageInfoModel = new RetrofitImageInfoModel(this);
    }

    @Override
    public void getDataFromURL(Context context, String url) {
        mRetrofitImageInfoModel.initRetrofitCall(context, url);
    }

    @Override
    public void onGetUrlSuccessImageInfo(String message, List<Item> itemInfo) {
        mGetDataView.onGetUrlSuccessImageInfo(message, itemInfo);
    }

    @Override
    public void onFailure(String message) {

    }


}
