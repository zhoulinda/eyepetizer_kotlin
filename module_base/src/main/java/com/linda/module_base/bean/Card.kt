package com.linda.module_base.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

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
) : Parcelable