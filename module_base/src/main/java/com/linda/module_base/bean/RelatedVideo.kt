package com.linda.module_base.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.linda.lib_common.CommonApplication
import com.linda.lib_common.utils.DisplayUtil
import kotlinx.android.parcel.Parcelize

/**
 * 描述 :     相关视频ItemBean
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/10
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class RelatedVideo(
    val dataType: String,
    val title: String,
    val id: Int,
    val description: String,
    val consumption: Consumption,
    val resourceType: String,
    val category: String,
    val cover: Cover,
    val author: Author
) : Parcelable {

    companion object {
        @JvmStatic
        @BindingAdapter("image")
        fun loadImage(view: ImageView, url: String) {
            if (!url.isNullOrEmpty()) {
                val roundedCorners = RoundedCorners(DisplayUtil.dip2px(3f))
                val options = RequestOptions.bitmapTransform(roundedCorners)
                Glide.with(CommonApplication.getContext())
                    .load(url)
                    .apply(options)
                    .into(view)
            }
        }
    }
}