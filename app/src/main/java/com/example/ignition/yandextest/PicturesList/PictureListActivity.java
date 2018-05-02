package com.example.ignition.yandextest.PicturesList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ignition.yandextest.Adapter.PicturesListAdapter;
import com.example.ignition.yandextest.FullImageSizeScreen.FullImageScreenActivity;
import com.example.ignition.yandextest.Models.DiskMetaDataModel;
import com.example.ignition.yandextest.R;
import com.example.ignition.yandextest.Utils.RecyclerItemClickListener;

import java.util.List;

public class PictureListActivity extends AppCompatActivity implements GetMetaDataContract.View {
    protected Presenter mPresenter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    PicturesListAdapter picturesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_list);
        mPresenter = new Presenter(this);
        mPresenter.getDataFromURL(getApplicationContext(), "");

        generatePictureList();
    }

    public void generatePictureList() {
        recyclerView = findViewById(R.id.list);
        linearLayoutManager = new GridLayoutManager(PictureListActivity.this, 2);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);


    }

    @Override
    public void onGetDataSuccess(String message, DiskMetaDataModel metaDataModelList) {
    }

    @Override
    public void onGetUrlSuccess(String message, List<String> imagesUrlList) {
        picturesListAdapter = new PicturesListAdapter(getApplicationContext(), imagesUrlList);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(), FullImageScreenActivity.class);
                        intent.putExtra("url of image", imagesUrlList.get(position));
                        startActivity(intent);
                    }
                }));
        recyclerView.setAdapter(picturesListAdapter);

    }

    @Override
    public void onGetDataFailure(String message) {
        Log.d("Status", message);
    }

}
