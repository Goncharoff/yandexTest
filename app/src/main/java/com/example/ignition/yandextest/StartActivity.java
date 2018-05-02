package com.example.ignition.yandextest;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ignition.yandextest.PicturesList.PictureListActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isOnline(this)) {
            startMainScreen();
            finish();
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.no_internet_error_ru), Toast.LENGTH_LONG).show();

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

    private void startMainScreen() {
        Intent intent = new Intent(this, PictureListActivity.class);
        startActivity(intent);
    }
}
