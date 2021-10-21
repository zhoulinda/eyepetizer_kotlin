package com.linda.module_video_detail.ui

import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.linda.module_base.constants.Constants
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.ui.BaseActivityV2
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
    BaseActivityV2<DetailActivityBrowsePictureBinding>(R.layout.detail_activity_browse_picture) {

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
        detailViewModel.run {
            detailData.observe(this@BrowsePictureActivity, Observer { data ->
                pictureInfoView.run {
                    setData(data)
                    setOnClickListener {
                        ARouter.getInstance().build(RouterPaths.PERSON_MAIN_ACTIVITY)
                            .withInt(Constants.USER_ID, data.owner.uid!!)
                            .withString(Constants.RESOURCE_TYPE, data.resourceType)
                            .navigation()
                    }
                }
                index.text = "1/" + data.urls.size
                viewPager.run {
                    adapter = PictureDetailPagerAdapter(this@BrowsePictureActivity, data.urls)
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
                            index.text = (position + 1).toString() + "/" + data.urls.size
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