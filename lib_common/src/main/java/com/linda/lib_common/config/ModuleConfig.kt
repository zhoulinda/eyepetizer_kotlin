package com.linda.lib_common.config

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/7/24
 */
interface ModuleConfig {

    companion object {

        private const val MODULE_BASE =
            "com.linda.module_base.BaseApplication"

        private const val MODULE_MAIN = "com.linda.module_main.MainApplication"

        private const val MODULE_LOGIN = "com.linda.module_login.LoginApplication"

        private const val MODULE_VIDEO_DETAIL =
            "com.linda.module_video_detail.VideoDetailApplication"

        private const val MODULE_NET = "com.linda.lib_net.NetApplication"

        val modules =
            arrayOf(MODULE_BASE, MODULE_MAIN, MODULE_LOGIN, MODULE_VIDEO_DETAIL, MODULE_NET)
    }
}