package com.example.ignition.yandextest.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ignition.yandextest.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class PicturesListAdapter extends RecyclerView.Adapter<PicturesListAdapter.ViewHolder> {
    private Context context;
    private List<String> imagesUrlList;


    public PicturesListAdapter(Context context, List<String> imagesUrlList) {
        this.imagesUrlList = imagesUrlList;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.tv_image_in_list);
        }
    }


    @Override
    public PicturesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PicturesListAdapter.ViewHolder holder, int position) {
        Picasso.get().load(imagesUrlList.get(position)).resize(520, 520).centerCrop().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return imagesUrlList.size();
    }
}
