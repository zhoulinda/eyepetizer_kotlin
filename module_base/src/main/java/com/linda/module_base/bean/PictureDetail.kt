package com.linda.module_base.bean

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/4
 */
data class PictureDetail(
    val title: String,
    val description: String,
    val consumption: Consumption,
    val owner: Owner,
    val urls: List<String>
)