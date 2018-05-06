package com.example.ignition.yandextest;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ignition.yandextest.PicturesList.PictureListActivity;

public class StartActivity extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initView();

        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    private void initView() {
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        progressBar.setVisibility(ProgressBar.INVISIBLE);
        if (isOnline(this)) {
            startPictureListScreen();
            finish();
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.no_internet_connection_eng), Toast.LENGTH_LONG).show();

        }
    }

    private boolean isOnline(Context context) {
        boolean isConnected = false;
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting()) {
            isConnected = true;
        }
        return isConnected;
    }

    private void startPictureListScreen() {
        Intent intent = new Intent(this, PictureListActivity.class);
        startActivity(intent);
    }
}
