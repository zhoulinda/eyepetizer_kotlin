package com.linda.module_base.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.linda.lib_common.CommonApplication
import com.linda.lib_common.utils.DateUtil
import com.linda.lib_common.utils.DisplayUtil
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.view_auto_play_ad.view.*

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/12
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class ItemData(
    val dataType: String?,
    val itemList: ArrayList<Card>?,
    val header: Header?,
    val content: Content?,
    val detail: Detail?,
    val cover: Cover?,
    val title: String?,
    val icon: String?,
    val description: String?,
    val resourceType: String?,
    val label: Label?,
    val image: String?,
    val text: String?,
    val duration: Int?,
    val rightText: String?,
    val count: Int?,
    val adIndex: Int?,
    val id: Int?,
    val tag: String?,
    val type: String?,
    val category: String?,
    val titleList: List<String>?,
    val bannerList: List<Banner>
) : Parcelable {

    fun getDuration(): String? {
        return duration?.let { DateUtil.getDuration(it) }
    }


    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String) {
            if (!url.isNullOrEmpty()) {
                Glide.with(CommonApplication.getContext())
                    .load(url)
                    .into(view)
            }
        }

        @JvmStatic
        @BindingAdapter("imageUrl", "roundedCorners")
        fun loadRoundedImage(view: ImageView, url: String, roundedCorners: Float) {
            if (!url.isNullOrEmpty()) {
                Glide.with(CommonApplication.getContext())
                    .load(url)
                    .apply(
                        RequestOptions.bitmapTransform(
                            RoundedCorners(
                                DisplayUtil.dip2px(
                                    roundedCorners
                                )
                            )
                        )
                    )
                    .into(view)
            }
        }

        @JvmStatic
        @BindingAdapter("circleImageUrl")
        fun loadCircleImage(view: ImageView, url: String) {
            if (!url.isNullOrEmpty()) {
                Glide.with(CommonApplication.getContext())
                    .load(url)
                    .apply(RequestOptions.bitmapTransform(CircleCrop()))
                    .into(view)
            }
        }
    }
}