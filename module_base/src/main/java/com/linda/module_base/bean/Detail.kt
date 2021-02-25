package com.linda.module_base.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/23
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Detail(
    val actionUrl: String,
    val adaptiveImageUrls: String,
    val adaptiveUrls: String,
    val canSkip: Boolean,
    val categoryId: Int,
    val countdown: Boolean,
    val cycleCount: Int,
    val description: String,
    val displayCount: Int,
    val displayTimeDuration: Int,
    val icon: String,
    val id: Int,
    val ifLinkage: Boolean,
    val imageUrl: String,
    val iosActionUrl: String,
    val linkageAdId: Int,
    val loadingMode: Int,
    val openSound: Boolean,
    val position: Int,
    val showActionButton: Boolean,
    val showImage: Boolean,
    val showImageTime: Int,
    val timeBeforeSkip: Int,
    val title: String,
    val url: String,
    val videoAdType: String,
    val videoType: String
) : Parcelable
