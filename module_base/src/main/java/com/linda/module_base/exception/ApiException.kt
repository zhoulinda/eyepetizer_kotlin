package com.linda.module_base.exception

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/3
 */
class ApiException(val code: Int, val errorMsg: String) : Exception() {

}