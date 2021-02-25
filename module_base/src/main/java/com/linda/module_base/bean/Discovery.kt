package com.linda.module_base.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 描述 :    发现列表数据
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/3
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Discovery(
    val count: Int,
    val total: Int,
    val nextPageUrl: String,
    val adExist: Boolean,
    val itemList: ArrayList<ItemData>
) : Parcelable