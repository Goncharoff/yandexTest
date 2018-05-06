package com.example.ignition.yandextest.ImageInfoActivity;

import android.content.Context;
import com.example.ignition.yandextest.Models.Item;
import java.util.List;

public interface GetImageInfoContract {

    interface OnGetDataListener {
        void onGetUrlSuccessImageInfo(String message, List<Item> itemInfo);
        void onFailure(String message);
    }


    interface ViewImageInfo {
        void onGetUrlSuccessImageInfo(String message, List<Item> itemInfo);
    }

    interface RetrofitInteracted {
        void initRetrofitCall(Context context, String url);
    }

    interface Presenter {
        void getDataFromURL(Context context, String url);
    }

}
