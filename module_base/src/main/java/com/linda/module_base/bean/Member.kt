package com.linda.module_base.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/23
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Member(
    val uid: Int,
    val nick: String,
    val avatar: String,
    val username: String,
    val description: String,
    val cover: String
) : Parcelable, Serializable