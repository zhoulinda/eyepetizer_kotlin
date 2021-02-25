package com.linda.module_base.config

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/7/24
 */
interface ModuleConfig {

    companion object {

        const val MODULE_MAIN = "com.linda.module_main.MainApplication"

        const val MODULE_NET = "com.linda.lib_net.NetApplication"

        val modules = arrayOf(MODULE_MAIN, MODULE_NET)
    }
}