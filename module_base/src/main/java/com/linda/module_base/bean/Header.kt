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
data class Header(
    val id: Int?,
    val title: String?,
    val name: String?,
    val actionUrl: String?,
    val icon: String?,
    val description: String?,
    val time: Long?,
    val showHateVideo: Boolean?,
    val rightText: String
) : Parcelable