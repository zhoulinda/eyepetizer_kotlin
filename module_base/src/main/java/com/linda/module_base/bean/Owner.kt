package com.linda.module_base.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.io.Serializable

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/17
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Owner(
    val actionUrl: String?,
    val avatar: String?,
    val birthday: Long?,
    val city: String?,
    val cover: String?,
    val description: String?,
    val expert: Boolean?,
    val followed: Boolean?,
    val gender: String?,
    val ifPgc: Boolean?,
    val job: String?,
    val library: String?,
    val limitVideoOpen: Boolean?,
    val nickname: String?,
    val registDate: Long?,
    val releaseDate: Long?,
    val uid: Int?,
    val university: String?,
    val userType: String?
) : Parcelable