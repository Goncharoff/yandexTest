package com.example.ignition.yandextest.FullImageSizeScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.ignition.yandextest.R;
import com.squareup.picasso.Picasso;

public class FullImageScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image_screen);

        initViews(getImageUrl());
    }

    public void initViews(String url) {
        ImageView imageView = findViewById(R.id.tv_image_in_list);
        Picasso.get().load(url).into(imageView);
    }

    public String getImageUrl() {
        return getIntent().getExtras().getString("url of image");
    }
}
