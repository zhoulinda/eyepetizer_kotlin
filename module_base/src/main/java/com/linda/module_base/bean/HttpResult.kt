package com.linda.module_base.bean

/**
 * 描述 :     请求结果公共bean
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/3
 */

data class HttpResult<T>(var errorCode: Int, var errorMessage: String, var data: T)