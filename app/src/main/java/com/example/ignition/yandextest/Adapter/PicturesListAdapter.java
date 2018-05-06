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

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, FullImageScreenActivity.class);
                intent.putExtra("imagePosition", getAdapterPosition());
                intent.putStringArrayListExtra("ImageUrls", imagesUrlList);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            });
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
        Picasso.get().load(imagesUrlList.get(position)).centerCrop().resize(520, 520).centerCrop().into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                if (holder.imageLoadingProgressBar != null) {
                    holder.imageLoadingProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(context, "Error while loading picture", Toast.LENGTH_SHORT).show();
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
}
