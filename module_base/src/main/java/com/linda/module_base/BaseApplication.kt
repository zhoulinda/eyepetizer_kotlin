package com.linda.module_base

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.linda.lib_common.CommonApplication
import com.linda.lib_common.utils.MyLogger
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout


/**
 * 描述 :    基类Application
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/7/23
 */
class BaseApplication : CommonApplication() {

    override fun init() {
        initARouter()
        initRefreshLayout()
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(getContext() as Application?)
    }

    private fun initRefreshLayout() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setPrimaryColors(
                getContext().resources.getColor(R.color.cl_ffffff),
                getContext().resources.getColor(R.color.cl_aaaaaa)
            )
            ClassicsHeader(context)
        }

        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
            ClassicsFooter(
                context
            ).setDrawableSize(20F)
        }
    }

}