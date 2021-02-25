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
data class Shield(
    val itemId: Int,
    val itemType: String,
    val shielded: Boolean
) : Parcelable