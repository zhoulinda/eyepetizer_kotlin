package com.linda.module_video_detail.ui

import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.linda.module_base.bean.VideoDetail
import com.linda.module_base.constants.Constants
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.ui.BaseActivity
import com.linda.module_video_detail.R
import com.linda.module_video_detail.adapter.PictureDetailPagerAdapter
import com.linda.module_video_detail.contract.PictureDetailContract
import com.linda.module_video_detail.presenter.PictureDetailPresenter
import kotlinx.android.synthetic.main.detail_activity_browse_picture.*
import kotlinx.android.synthetic.main.detail_view_picture_info.*

/**
 * 描述 :    大图浏览
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/17
 */
@Route(path = RouterPaths.DETAIL_BROWSE_PICTURE_ACTIVITY)
class BrowsePictureActivity : BaseActivity(R.layout.detail_activity_browse_picture),
    PictureDetailContract.View {

    @JvmField
    @Autowired(name = Constants.PICTURE_ID)
    var pictureId = 0

    @JvmField
    @Autowired(name = Constants.RESOURCE_TYPE)
    var resourceType = ""

    private val pictureDetailPresenter by lazy { PictureDetailPresenter(this) }
    private var videoDetail: VideoDetail? = null

    override fun initView() {
        setImmerseLayout(browsePicture)
        ARouter.getInstance().inject(this)
        portrait.setOnClickListener {
            ARouter.getInstance().build(RouterPaths.PERSON_MAIN_ACTIVITY)
                .withInt(Constants.USER_ID, videoDetail?.owner?.uid!!)
                .withString(Constants.RESOURCE_TYPE, videoDetail?.resourceType)
                .navigation()
        }
    }

    override fun initData() {
        pictureDetailPresenter.getPictureDetail(pictureId, resourceType)
    }

    override fun onGetPictureDetailSuccess(videoDetail: VideoDetail) {
        this.videoDetail = videoDetail
        pictureInfoView.setData(videoDetail)
        index.text = "1/" + videoDetail.urls.size
        viewPager.run {
            adapter = PictureDetailPagerAdapter(this@BrowsePictureActivity, videoDetail.urls)
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
                    index.text = (position + 1).toString() + "/" + videoDetail.urls.size
                }
            })
        }
    }

    override fun onGetPictureDetailError() {

    }
}