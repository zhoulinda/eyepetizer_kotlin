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
import kotlinx.android.synthetic.main.item_column.view.*

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/12
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Card(
    val adIndex: Int?,
    val data: DataBean?,
    val id: Int?,
    val type: String?
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class DataBean(
    val header: Header?,
    val content: Content?,
    val actionUrl: String?,
    val image: String?,
    val bgPicture: String?,
    val dataType: String?,
    val subTitle: String?,
    val title: String?
) : Parcelable {

    companion object {

        @JvmStatic
        @BindingAdapter("setSrc", "roundedCorners")
        fun setSrc(view: ImageView, url: String, roundedCorners: Float) {
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
        @BindingAdapter("setSrc2", "roundedCorners")
        fun setSrc2(view: ImageView, url: String, roundedCorners: Float) {
            if (!url.isNullOrEmpty()) {
                Glide.with(CommonApplication.getContext())
                    .load(url)
                    .override(
                        (DisplayUtil.getScreenWidth(CommonApplication.getContext()) - DisplayUtil.dip2px(
                            24f
                        ) / 2), DisplayUtil.dip2px(100f)
                    )
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
    }
}