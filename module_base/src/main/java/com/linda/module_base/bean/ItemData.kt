package com.linda.module_base.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/12
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class ItemData(
    val dataType: String?,
    val itemList: ArrayList<Card>?,
    val header: Header?,
    val content: Content?,
    val detail: Detail?,
    val cover: Cover?,
    val title: String?,
    val icon: String?,
    val description: String?,
    val resourceType: String?,
    val label: Label?,
    val image: String?,
    val text: String?,
    val duration: Int?,
    val rightText: String?,
    val count: Int?,
    val adIndex: Int?,
    val id: Int?,
    val tag: String?,
    val type: String?,
    val category: String?,

    val titleList: List<String>?,
    val bannerList: List<Banner>
) : Parcelable