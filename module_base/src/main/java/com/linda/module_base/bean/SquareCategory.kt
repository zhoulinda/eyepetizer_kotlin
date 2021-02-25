package com.linda.module_base.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 描述 :   首页广场分类
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/21
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class SquareCategory(
    val type: String,
    val id: Int,
    val title: String,
    val image: String,
    val actionUrl: String,
    val description: String
) : Parcelable
