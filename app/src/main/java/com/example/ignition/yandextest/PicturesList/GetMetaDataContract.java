package com.example.ignition.yandextest.PicturesList;

import android.content.Context;


import java.util.ArrayList;

public interface GetMetaDataContract {
    interface OnGetDataListener {
        void onGetUrlSuccess(String message, ArrayList<String> imagesUrlList);

        void onFailure(String message);
    }


    interface RetrofitInteracted {
        void initRetrofitCall(Context context, String url);
    }

    interface Presenter {
        void getDataFromURL(Context context, String url);
    }

    interface View {
        void onGetUrlSuccess(String message, ArrayList<String> imagesUrlList);

        void onGetDataFailure(String message);
    }

}
