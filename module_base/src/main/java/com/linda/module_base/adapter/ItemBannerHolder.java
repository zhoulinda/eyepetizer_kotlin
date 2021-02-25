package com.linda.module_base.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.linda.module_base.view.VideoCollectionHorizontalScrollCardItemView;

public class ItemBannerHolder extends RecyclerView.ViewHolder {
    public VideoCollectionHorizontalScrollCardItemView itemView;

    public ItemBannerHolder(@NonNull View view) {
        super(view);
        this.itemView = (VideoCollectionHorizontalScrollCardItemView) view;
    }
}
