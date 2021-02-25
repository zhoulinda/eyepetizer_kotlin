package com.linda.module_base.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/10
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Author(
    val approvedNotReadyVideoCount: Int?,
    val description: String?,
    val expert: Boolean?,
    val follow: Follow?,
    val icon: String?,
    val id: Int?,
    val ifPgc: Boolean?,
    val latestReleaseTime: Long?,
    val link: String?,
    val name: String?,
    val recSort: Int?,
    val shield: Shield?,
    val videoNum: Int?
) : Parcelable