package com.linda.module_base.utils

import android.graphics.drawable.Drawable
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.linda.lib_common.utils.DisplayUtil
import com.linda.module_base.adapter.ColumnCardAdapter
import com.linda.module_base.adapter.SquareCardAdapter
import com.linda.module_base.adapter.VideoCollectionBannerImageAdapter
import com.linda.module_base.bean.Card
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter

@BindingAdapter("items")
fun setItems(banner: Banner<Card, BannerImageAdapter<Card>>, items: ArrayList<Card>) {
    with(banner.adapter as BannerImageAdapter<Card>) {
        setDatas(items)
        notifyDataSetChanged()
    }
}

@BindingAdapter("bannerItems")
fun setBannerItems(
    banner: Banner<Card, VideoCollectionBannerImageAdapter>,
    items: ArrayList<Card>
) {
    with(banner.adapter as VideoCollectionBannerImageAdapter) {
        setDatas(items)
        notifyDataSetChanged()
    }
}

@BindingAdapter("items")
fun setItems(recyclerView: RecyclerView, items: ArrayList<Card>) {
    when (recyclerView.adapter) {
        is SquareCardAdapter ->
            with(recyclerView.adapter as SquareCardAdapter) {
                setData(items)
            }
        is ColumnCardAdapter ->
            with(recyclerView.adapter as ColumnCardAdapter) {
                setData(items)
            }
    }
}

@BindingAdapter(
    "imageUrl",
    "imagePlaceholder",
    "roundedCorners",
    "circleCropImage",
    "crossFadeImage",
    "overrideImageWidth",
    "overrideImageHeight",
    "imageLoadListener",
    requireAll = false
)
fun bindImage(
    imageView: ImageView,
    imageUrl: String?,
    placeholder: Int? = null,
    roundedCorners: Float? = null,
    circleCrop: Boolean? = false,
    crossFade: Boolean? = false,
    overrideWidth: Int? = null,
    overrideHeight: Int? = null,
    listener: RequestListener<Drawable>?
) {
    if (imageUrl == null) return
    var request = Glide.with(imageView.context).load(imageUrl)
    if (placeholder != null) {
        request = request.placeholder(placeholder)
    }
    if (roundedCorners != null) {
        request = request.apply(
            RequestOptions.bitmapTransform(
                RoundedCorners(
                    DisplayUtil.dip2px(
                        roundedCorners
                    )
                )
            )
        )
    }
    if (circleCrop == true) {
        request = request.circleCrop()
    }
    if (crossFade == true) {
        request = request.transition(DrawableTransitionOptions.withCrossFade())
    }
    if (overrideWidth != null && overrideHeight != null) {
        request = request.override(overrideWidth, overrideHeight)
    }
    if (listener != null) {
        request = request.listener(listener)
    }
    request.into(imageView)
}
