package com.linda.module_base.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/11
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Tag(
    val actionUrl: String?,
    val bgPicture: String?,
    val communityIndex: Int?,
    val haveReward: Boolean?,
    val headerImage: String?,
    val id: Int?,
    val ifNewest: Boolean?,
    val name: String?,
    val tagRecType: String?,
    val desc: String?
) : Parcelable