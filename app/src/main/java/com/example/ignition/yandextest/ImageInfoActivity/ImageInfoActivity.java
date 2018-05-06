package com.example.ignition.yandextest.ImageInfoActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ignition.yandextest.Models.Item;
import com.example.ignition.yandextest.R;

import java.util.List;

public class ImageInfoActivity extends AppCompatActivity implements GetImageInfoContract.ViewImageInfo {
    ImageInfoPresenter imageInfoPresenter;
    TextView imageNameTV;
    TextView imageSizeTV;
    TextView imagePathTV;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_info);

        imageInfoPresenter = new ImageInfoPresenter(this);
        imageInfoPresenter.getDataFromURL(getApplicationContext(), "");

        position = getImagePosition();

        initViews();
    }

    void initViews() {
        imageNameTV = findViewById(R.id.nameStatic);
        imageSizeTV = findViewById(R.id.sizeStatic);
        imagePathTV = findViewById(R.id.pathStatic);
    }


    //get position of image from recycler view
    public int getImagePosition() {
        return getIntent().getExtras().getInt("positionFromImageFullList");
    }


    @Override
    public void onGetUrlSuccessImageInfo(String message, List<Item> itemInfo) {
        imageNameTV.setText(getString(R.string.name_eng,itemInfo.get(position).getName()));
        imageSizeTV.setText(getString(R.string.size_eng, itemInfo.get(position).getSize()));
        imagePathTV.setText(getString(R.string.path_eng, itemInfo.get(position).getPath()));
    }
}
