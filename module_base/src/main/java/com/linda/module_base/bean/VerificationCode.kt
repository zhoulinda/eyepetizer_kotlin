package com.linda.module_base.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/24
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class VerificationCode(
    val error: Int,
    val msg: String
) : Parcelable