package com.linda.module_video_detail.ui

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.linda.module_base.bean.BaseListData
import com.linda.module_base.bean.RelatedVideo
import com.linda.module_base.bean.VideoDetail
import com.linda.module_base.constants.Constants
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.listener.OnViewClickListener
import com.linda.module_base.ui.BaseActivity
import com.linda.module_video_detail.R
import com.linda.module_video_detail.contract.VideoDetailContract
import com.linda.module_video_detail.presenter.VideoDetailPresenter
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import kotlinx.android.synthetic.main.activity_video_detail.*
import kotlinx.android.synthetic.main.view_video_info.view.*


/**
 * 描述 :     视频详情
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/8
 */
@Route(path = RouterPaths.DETAIL_VIDEO_DETAIL_ACTIVITY)
class VideoDetailActivity : BaseActivity(), VideoDetailContract.View {

    @JvmField
    @Autowired(name = Constants.VIDEO_ID)
    var videoId = 0

    @JvmField
    @Autowired(name = Constants.RESOURCE_TYPE)
    var resourceType = ""

    private var videoDetailPresenter: VideoDetailPresenter? = null
    private var relatedVideoList: BaseListData<RelatedVideo>? = null
    private var orientationUtils: OrientationUtils? = null

    override fun getLayoutResId(): Int {
        return R.layout.activity_video_detail
    }

    override fun initView() {
        setTransStatusBar()
        ARouter.getInstance().inject(this)
        mRelateVideoView.setOnViewClickListener(object : OnViewClickListener<Any> {
            override fun onViewClick(view: View, data: Any) {
                when (view.id) {
                    R.id.lookMore ->
                        relatedVideoList?.let { relatedVideoList ->
                            mRelateVideoView.setData(
                                relatedVideoList,
                                true
                            )
                        }
                    R.id.mRootView ->
                        if (data is RelatedVideo)
                            ARouter.getInstance().build(RouterPaths.DETAIL_VIDEO_DETAIL_ACTIVITY)
                                .withFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                .withInt(Constants.VIDEO_ID, data.id)
                                .withString(Constants.RESOURCE_TYPE, data.resourceType)
                                .navigation()
                }
            }
        })
    }

    override fun initData() {
        videoDetailPresenter = VideoDetailPresenter(this)
        videoId.let {
            videoDetailPresenter?.getVideoDetail(it, resourceType)
            videoDetailPresenter?.getVideoRelated(it)
        }
    }

    override fun onGetVideoDetailSuccess(videoDetail: VideoDetail) {
        val imageView = ImageView(this)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        Glide.with(this)
            .load(videoDetail.cover.detail)
            .into(imageView)
        videoPlayer.thumbImageView = imageView
        videoPlayer.setUp(videoDetail.playUrl, true, "")
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true)
        //设置旋转
        val orientationUtils = OrientationUtils(this, videoPlayer)
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.fullscreenButton
            .setOnClickListener { orientationUtils.resolveByClick() }
        videoPlayer.backButton.setOnClickListener { onBackPressed() }
        videoPlayer.startPlayLogic()
        Glide.with(this).load(videoDetail.cover.blurred).into(mDetailBg)

        mVideoInfoView.setData(videoDetail)
        mVideoInfoView.mAvatar.setOnClickListener {
            ARouter.getInstance().build(RouterPaths.PERSON_MAIN_ACTIVITY)
                .withInt(Constants.USER_ID, videoDetail.author.id!!)
                .navigation()
        }
    }

    override fun onGetVideoDetailError() {
    }

    override fun onGetVideoRelatedSuccess(relatedVideoList: BaseListData<RelatedVideo>) {
        this.relatedVideoList = relatedVideoList
        mRelateVideoView.setData(relatedVideoList, false)
    }

    override fun onGetVideoRelatedError() {
    }

    override fun onPause() {
        super.onPause()
        videoPlayer.onVideoPause()
    }

    override fun onResume() {
        super.onResume()
        videoPlayer.onVideoResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
        if (orientationUtils != null) orientationUtils?.releaseListener()
    }

    override fun onBackPressed() {
        //先返回正常状态
        if (orientationUtils?.screenType === ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            videoPlayer.fullscreenButton.performClick()
            return
        }
        //释放所有
        videoPlayer.setVideoAllCallBack(null)
        super.onBackPressed()
    }

}