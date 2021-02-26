package com.linda.module_base.config

/**
 * 描述 :     Component Application配置
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/7/24
 */
interface ModuleConfig {

    companion object {

        private const val MODULE_MAIN = "com.linda.module_main.MainApplication"

        private const val MODULE_LOGIN = "com.linda.module_login.LoginApplication"

        private const val MODULE_VIDEO_DETAIL =
            "com.linda.module_video_detail.VideoDetailApplication"

        val modules = arrayOf(MODULE_MAIN, MODULE_LOGIN, MODULE_VIDEO_DETAIL)
    }
}