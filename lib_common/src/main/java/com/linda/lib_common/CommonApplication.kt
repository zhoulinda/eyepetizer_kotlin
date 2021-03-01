package com.linda.lib_common

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.linda.lib_common.config.ModuleConfig

/**
 * 描述 :     公共Application
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/4
 */
abstract class CommonApplication : MultiDexApplication(), IBaseApplication {

    companion object {
        private var mContext: Context? = null
        fun getContext() = mContext!!
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
        initComponent()
    }

    private fun initComponent() {
        for (module in ModuleConfig.modules) {
            try {
                val clazz = Class.forName(module)
                val baseApplication: IBaseApplication =
                    clazz.newInstance() as IBaseApplication
                baseApplication.init()
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            }
        }
    }
}