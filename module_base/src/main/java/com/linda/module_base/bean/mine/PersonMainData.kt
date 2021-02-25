package com.linda.module_base.bean.mine

import android.annotation.SuppressLint
import android.os.Parcelable
import com.linda.module_base.bean.Banner
import com.linda.module_base.bean.Follow
import kotlinx.android.parcel.Parcelize

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/5
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class PersonMainData(val tabInfo: TabInfo, var pgcInfo: PgcInfo, val userInfo: PgcInfo) :
    Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class TabInfo(
    val tabList: List<TabData>
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class TabData(
    val id: Int,
    val name: String,
    val apiUrl: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class PgcInfo(
    val icon: String,
    val name: String,
    val brief: String,
    val description: String,
    val cover: String,
    val followCount: Int,
    val videoCount: Int,
    val myFollowCount: Int
) : Parcelable