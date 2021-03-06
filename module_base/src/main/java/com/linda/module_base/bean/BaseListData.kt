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
data class BaseListData<T : Parcelable>(
    val itemList: ArrayList<VideoItem<T>>,
    val count: Int,
    val total: Int,
    val nextPageUrl: String,
    val adExist: Boolean
) : Parcelable