package com.example.ignition.yandextest.FullImageSizeScreen;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.ignition.yandextest.ImageInfoActivity.ImageInfoActivity;
import com.example.ignition.yandextest.R;
import com.example.ignition.yandextest.Utils.ImageSwitcherPicasso;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FullImageScreenActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory, GestureDetector.OnGestureListener {

    private ImageSwitcher mImageSwitcher;
    private GestureDetector mGestureDetector;
    ImageView popupImage;
    TextView counterTextView;
    //constants for touch events
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;

    int position;
    ArrayList<String> imageUrlArray;
    ImageSwitcherPicasso mImageSwitcherPicasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
        initViews();
        settingImageSwitcher();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            popupImage.setVisibility(View.GONE);

        }
    }

    public void hideActionBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_full_image_screen);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        } else {
            Toast.makeText(getApplicationContext(), "Application actionbar error", Toast.LENGTH_LONG).show();
        }
    }

    public void initViews() {
        mImageSwitcher = findViewById(R.id.imageSwitcher);
        popupImage = findViewById(R.id.imageMenu);
        counterTextView = findViewById(R.id.counterView);
        popupImage.setOnClickListener(this::showPopupMenu);
    }

    public void settingImageSwitcher() {

        initAnimation();

        mImageSwitcher.setFactory(this);

        position = getImagePosition();
        imageUrlArray = getImageUrlArray();

        setCounterTextView();

        mImageSwitcherPicasso = new ImageSwitcherPicasso(getApplicationContext(), mImageSwitcher);
        mGestureDetector = new GestureDetector(this, this);

        loadPictureIntoImageSwitcher(position, imageUrlArray);
    }

    //setting scrolling images animation
    public void initAnimation() {
        Animation inAnimation = new AlphaAnimation(0, 1);
        inAnimation.setDuration(1000);
        Animation outAnimation = new AlphaAnimation(1, 0);
        outAnimation.setDuration(1000);

        mImageSwitcher.setInAnimation(inAnimation);
        mImageSwitcher.setOutAnimation(outAnimation);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onFling(MotionEvent motionEvent1, MotionEvent motionEvent2, float velocityX, float velocityY) {
        try {
            if (Math.abs(motionEvent1.getY() - motionEvent2.getY()) > SWIPE_MAX_OFF_PATH) {
                return false;
            }
            // from right to left
            if (motionEvent1.getX() - motionEvent2.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                setPositionNext();
                setCounterTextView();
                loadPictureIntoImageSwitcher(position, imageUrlArray);
            } else if (motionEvent1.getX() - motionEvent2.getX() < SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                // from left to right
                setPositionPrev();
                setCounterTextView();
                loadPictureIntoImageSwitcher(position, imageUrlArray);
            }
        } catch (Exception e) {
            // nothing
            return true;
        }
        return true;
    }

    //get new position of element from array of images URL's  if swap in right direction
    public void setPositionNext() {
        position++;
        if (position > imageUrlArray.size() - 1) {
            position = 0;
        }
    }

    //get new position of element from array of images URL's  if swap in left direction
    public void setPositionPrev() {
        position--;
        if (position < 0) {
            position = imageUrlArray.size() - 1;
        }
    }

    public void loadPictureIntoImageSwitcher(int position, ArrayList<String> imageUrlArray) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Picasso.get().load(imageUrlArray.get(position)).resize(getWindowManager().getDefaultDisplay().getWidth(), getWindowManager().getDefaultDisplay().getHeight()).into(mImageSwitcherPicasso);
        } else {
            Picasso.get().load(imageUrlArray.get(position)).into(mImageSwitcherPicasso);
        }
    }

    //image view factory for image view switcher
    @Override
    public View makeView() {
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT));
        return imageView;
    }


    //get array of URL's from recycler view
    public ArrayList<String> getImageUrlArray() {
        return getIntent().getExtras().getStringArrayList("ImageUrls");
    }

    //get position of image from recycler view
    public int getImagePosition() {
        return getIntent().getExtras().getInt("imagePosition");
    }

    public void setCounterTextView() {
        counterTextView.setText(getString(R.string.counter_tv, position + 1, imageUrlArray.size()));
    }

    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.popup_menu);

        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.imageInfo:
                    startImageInfoActicity();
                    return true;
                case R.id.turnLeft:
                    mImageSwitcher.setRotation(mImageSwitcher.getRotation() - 90);
                    return true;
                case R.id.turnRight:
                    mImageSwitcher.setRotation(mImageSwitcher.getRotation() + 90);
                    return true;
                default:
                    return false;
            }
        });

        popupMenu.show();
    }

    public void startImageInfoActicity() {
        Intent intent = new Intent(this, ImageInfoActivity.class);
        intent.putExtra("positionFromImageFullList", position);
        startActivity(intent);
    }

    //unusable autogenerated methods for GestureDetector.OnGestureListener
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        //make dark view
        return true;
    }
}


