package com.example.ignition.yandextest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ignition.yandextest.FullImageSizeScreen.FullImageScreenActivity;
import com.example.ignition.yandextest.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class PicturesListAdapter extends RecyclerView.Adapter<PicturesListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> imagesUrlList;

    private static final int RESIZING_VALUE = 520;
    public  static final String KEY_FOR_IMAGE_POSITION_IN_BUNDLE=  "imagePosition";
    public  static final String KEY_FOR_ARRAY_OF_IMAGES_URL_IN_BUNDLE= "ImageUrls";

    public PicturesListAdapter(Context context, ArrayList<String> imagesUrlList) {
        this.imagesUrlList = imagesUrlList;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ProgressBar imageLoadingProgressBar;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.tv_image_in_list);
            imageLoadingProgressBar = itemView.findViewById(R.id.imageLoadingProgressBar);
            imageLoadingProgressBar.setVisibility(View.VISIBLE);

            itemView.setOnClickListener(v -> onItemViewClicked(v, getAdapterPosition()));

        }
    }

    @Override
    @NonNull
    public PicturesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PicturesListAdapter.ViewHolder holder, int position) {

        /**
         * loading images from disk, using picasso lib and resizing it on constant value
         * callback for showing progressbar while loading picture
         */

        Picasso.get().load(imagesUrlList.get(position)).centerCrop().resize(RESIZING_VALUE, RESIZING_VALUE).centerCrop().into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                if (holder.imageLoadingProgressBar != null) {
                    holder.imageLoadingProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(context, R.string.error_during_loading_picture, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void clearList() {
        imagesUrlList.clear();
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return imagesUrlList.size();
    }

    /**
     * Handles sign in button click
     *
     * @param itemView        - clicked image in recycler view
     * @param adapterPosition - position image in adapter
     */
    private void onItemViewClicked(View itemView, int adapterPosition) {
        Intent intent = new Intent(context, FullImageScreenActivity.class);
        intent.putExtra(KEY_FOR_IMAGE_POSITION_IN_BUNDLE, adapterPosition);
        intent.putStringArrayListExtra(KEY_FOR_ARRAY_OF_IMAGES_URL_IN_BUNDLE, imagesUrlList);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
