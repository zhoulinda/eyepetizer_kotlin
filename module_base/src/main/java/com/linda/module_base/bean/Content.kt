package com.linda.module_base.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/10
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Content(
    val type: String?,
    val data: DataBean?,
    val id: Int?,
    val adIndex: Int?
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class DataBean(
        val dataType: String?,
        val id: Int?,
        val title: String?,
        val description: String?,
        val library: String?,
        val tags: ArrayList<Tag>?,
        val resourceType: String?,
        val cover: Cover?,
        val consumption: Consumption?,
        val author: Author?,
        val width: Float?,
        val height: Float?,
        val owner: Owner?,
        val urls: List<String>?,
        val playUrl: String?,
        val date: Long
    ) : Parcelable
}