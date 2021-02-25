package com.linda.module_base.bean

import android.annotation.SuppressLint
import android.os.Parcelable
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
) : Parcelable