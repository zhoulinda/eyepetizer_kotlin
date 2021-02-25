package com.linda.module_base.utils

import android.content.Context
import android.net.Uri
import androidx.appcompat.widget.ViewUtils
import com.alibaba.android.arouter.launcher.ARouter
import com.linda.module_base.bean.EntryItem
import com.linda.module_base.constants.RouterPaths

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/25
 */
class SchemeFactory {

    companion object {

        private const val SCHEME = "eyepetizer_kotlin://"

        private fun startByForward(context: Context, entryItem: EntryItem) {

            startByForward(context, entryItem.forward)
        }

        private fun startByForward(context: Context, entry: String) {
            if (entry.startsWith("http://") || entry.startsWith("https://")) {
                ARouter.getInstance().build(RouterPaths.WEBVIEW_ACTIVITY).navigation()
                return
            }

            if (entry.startsWith(SCHEME + "http://") || entry.startsWith(SCHEME + "https://")) {
                ARouter.getInstance().build(RouterPaths.WEBVIEW_ACTIVITY).navigation()
                return
            }

            val uri = Uri.parse(entry)

            when (uri.host) {

            }
        }
    }
}