package com.linda.module_base.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/10
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Consumption(
    val collectionCount: Int?,
    val realCollectionCount: Int?,
    val replyCount: Int?,
    val shareCount: Int?
) : Parcelable