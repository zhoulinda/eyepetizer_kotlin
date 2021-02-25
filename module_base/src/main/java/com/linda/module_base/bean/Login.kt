package com.linda.module_base.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

/**
 * 描述 :     登录bean
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/23
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Login(
    val error: Int,
    val msg: String,
    val member: Member
) : Parcelable
