package com.linda.module_base.constants

/**
 * 描述 :     ARouter path
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/7/24
 */
interface RouterPaths {

    companion object {

        const val HOME_FRAGMENT = "/home/home_fragment"

        const val COMMUNITY_FRAGMENT = "/community/community_fragment"

        const val NOTIFICATION_FRAGMENT = "/notification/notification_fragment"

        const val MINE_FRAGMENT = "/mine/mine_fragment"

        const val LOGIN_ACTIVITY = "/login/login_activity"

        const val REGISTER_ACTIVITY = "/login/register_activity"

        const val PERSON_MAIN_ACTIVITY = "/mine/person_main_activity"

        const val PERSON_HOME_FRAGMENT = "/mine/person_home_fragment"

        const val DISCOVER_FRAGMENT = "/discover/discover_fragment"

        const val DAILY_FRAGMENT = "/daily/daily_fragment"

        const val RECOMMEND_FRAGMENT = "/community/recommend_fragment"

        const val ATTENTION_FRAGMENT = "/community/attention_fragment"

        const val DETAIL_VIDEO_DETAIL_ACTIVITY = "/detail/video_detail_activity"

        const val DETAIL_BROWSE_PICTURE_ACTIVITY = "/detail/browse_picture_activity"

        const val DETAIL_BROWSE_PICTURE_FRAGMENT = "/detail/browse_picture_fragment"

        const val WEBVIEW_ACTIVITY = "/base/webView_activity"

        val MAIN_FRAGMENTS =
            arrayOf(HOME_FRAGMENT, COMMUNITY_FRAGMENT, NOTIFICATION_FRAGMENT, MINE_FRAGMENT)

        val HOME_FRAGMENTS = arrayOf(DISCOVER_FRAGMENT, DAILY_FRAGMENT)

        val COMMUNITY_FRAGMENTS = arrayOf(RECOMMEND_FRAGMENT, ATTENTION_FRAGMENT)
    }
}