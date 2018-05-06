package com.example.ignition.yandextest.PicturesList;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.ignition.yandextest.Adapter.PicturesListAdapter;
import com.example.ignition.yandextest.Models.DiskMetaDataModel;
import com.example.ignition.yandextest.R;

import java.util.ArrayList;

public class PictureListActivity extends AppCompatActivity implements GetMetaDataContract.View {
    protected Presenter mPresenter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    PicturesListAdapter picturesListAdapter;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_list);
        mPresenter = new Presenter(this);
        mPresenter.getDataFromURL(getApplicationContext(), "");

        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();

        generatePictureList();
    }

    public void initViews() {
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        recyclerView = findViewById(R.id.list);

        //swipe refresh logic
        swipeRefreshLayout.setOnRefreshListener(() -> {
            picturesListAdapter.clearList();
            mPresenter.getDataFromURL(getApplicationContext(), "");
            // cancel the Visual indication of a refresh
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    public void generatePictureList() {
        linearLayoutManager = new GridLayoutManager(PictureListActivity.this, 2);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
    }



    @Override
    public void onGetUrlSuccess(String message, ArrayList<String> imagesUrlList) {
        picturesListAdapter = new PicturesListAdapter(getApplicationContext(), imagesUrlList);
        recyclerView.setAdapter(picturesListAdapter);
    }

    @Override
    public void onGetDataFailure(String message) {
        Log.d("Status", message);
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

}
