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
import kotlinx.android.synthetic.main.activity_browse_picture.*
import kotlinx.android.synthetic.main.view_picture_info.*

/**
 * 描述 :    大图浏览
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/17
 */
@Route(path = RouterPaths.DETAIL_BROWSE_PICTURE_ACTIVITY)
class BrowsePictureActivity : BaseActivity(), PictureDetailContract.View {

    @JvmField
    @Autowired(name = Constants.PICTURE_ID)
    var pictureId = 0

    @JvmField
    @Autowired(name = Constants.RESOURCE_TYPE)
    var resourceType = ""

    private var pictureDetailPresenter: PictureDetailPresenter? = null
    private var detailAdapter: PictureDetailPagerAdapter? = null
    private var videoDetail: VideoDetail? = null

    override fun getLayoutResId(): Int {
        return R.layout.activity_browse_picture
    }

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
        pictureDetailPresenter = PictureDetailPresenter(this)
        pictureDetailPresenter?.getPictureDetail(pictureId, resourceType)
    }

    override fun onGetPictureDetailSuccess(videoDetail: VideoDetail) {
        this.videoDetail = videoDetail
        mPictureInfoView.setData(videoDetail)
        mIndex.text = "1/" + videoDetail.urls.size
        detailAdapter = PictureDetailPagerAdapter(this, videoDetail.urls)
        mViewPager.adapter = detailAdapter
        mViewPager.currentItem = 0
        mViewPager.addOnPageChangeListener(object : OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                mIndex.text = (position + 1).toString() + "/" + videoDetail.urls.size
            }
        })
    }

    override fun onGetPictureDetailError() {

    }
}