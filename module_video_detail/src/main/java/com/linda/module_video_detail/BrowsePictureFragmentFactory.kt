package com.linda.module_video_detail

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.impl.IFragmentFactory

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/17
 */
@Route(path = RouterPaths.DETAIL_BROWSE_PICTURE_FRAGMENT)
class BrowsePictureFragmentFactory : IFragmentFactory {

    override fun createFragment(position: Int): Fragment {
        return ARouter.getInstance().build(RouterPaths.DETAIL_BROWSE_PICTURE_FRAGMENT)
            .navigation() as Fragment
    }

    override fun init(context: Context?) {
    }
}