package com.linda.module_video_detail.ui

import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.linda.module_base.constants.Constants
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.ui.BaseActivity
import com.linda.module_video_detail.R
import com.linda.module_video_detail.adapter.PictureDetailPagerAdapter
import com.linda.module_video_detail.databinding.DetailActivityBrowsePictureBinding
import com.linda.module_video_detail.model.VideoDetailViewModel
import com.linda.module_video_detail.repository.VideoDetailRepository
import kotlinx.android.synthetic.main.detail_activity_browse_picture.*

/**
 * 描述 :    大图浏览
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/17
 */
@Route(path = RouterPaths.DETAIL_BROWSE_PICTURE_ACTIVITY)
class BrowsePictureActivity :
    BaseActivity<DetailActivityBrowsePictureBinding>(R.layout.detail_activity_browse_picture) {

    @JvmField
    @Autowired(name = Constants.PICTURE_ID)
    var pictureId = 0

    @JvmField
    @Autowired(name = Constants.RESOURCE_TYPE)
    var resourceType = ""

    private val detailViewModel by lazy { VideoDetailViewModel(VideoDetailRepository()) }

    override fun initView() {
        setImmerseLayout(browsePicture)
        ARouter.getInstance().inject(this)
        binding?.viewModel = detailViewModel.apply {
            detailData.observe(this@BrowsePictureActivity, Observer { data ->
                portrait.setOnClickListener {
                    ARouter.getInstance().build(RouterPaths.PERSON_MAIN_ACTIVITY)
                        .withInt(Constants.USER_ID, data.owner.uid!!)
                        .withString(Constants.RESOURCE_TYPE, data.resourceType)
                        .navigation()
                }
                viewPager.run {
                    adapter =
                        data.urls?.let { PictureDetailPagerAdapter(this@BrowsePictureActivity, it) }
                    currentItem = 0
                    addOnPageChangeListener(object : OnPageChangeListener {
                        override fun onPageScrollStateChanged(state: Int) {}
                        override fun onPageScrolled(
                            position: Int,
                            positionOffset: Float,
                            positionOffsetPixels: Int
                        ) {
                        }

                        override fun onPageSelected(position: Int) {
                            detailViewModel.setCurrentIndex(position + 1)
                        }
                    })
                }
            })
        }
    }

    override fun initData() {
        detailViewModel.getVideoDetail(pictureId, resourceType)
    }
}