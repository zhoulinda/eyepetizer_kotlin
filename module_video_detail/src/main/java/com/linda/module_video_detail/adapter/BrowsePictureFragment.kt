package com.linda.module_video_detail.adapter

import com.alibaba.android.arouter.facade.annotation.Route
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.ui.BaseFragment
import com.linda.module_video_detail.R

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/17
 */
@Route(path = RouterPaths.DETAIL_BROWSE_PICTURE_FRAGMENT)
class BrowsePictureFragment : BaseFragment() {

    override fun getLayoutResId(): Int {
        return R.layout.fragment_browse_picture
    }

    override fun initView() {
    }

    override fun initData() {
    }
}