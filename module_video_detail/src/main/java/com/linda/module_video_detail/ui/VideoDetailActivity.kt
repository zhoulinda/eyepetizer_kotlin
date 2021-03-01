package com.linda.module_video_detail.ui

import android.content.Intent
import android.content.res.Configuration
import android.view.View
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
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import kotlinx.android.synthetic.main.detail_activity_video_detail.*
import kotlinx.android.synthetic.main.detail_view_video_info.view.*

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

    private var isPlay = false
    private var isPause = false

    private var videoDetailPresenter: VideoDetailPresenter? = null
    private var relatedVideoList: BaseListData<RelatedVideo>? = null
    private var orientationUtils: OrientationUtils? = null

    override fun getLayoutResId(): Int {
        return R.layout.detail_activity_video_detail
    }

    override fun initView() {
        setTransStatusBar()
        ARouter.getInstance().inject(this)
        relateVideoView.setOnViewClickListener(object : OnViewClickListener<Any> {
            override fun onViewClick(view: View, data: Any) {
                when (view.id) {
                    R.id.lookMore ->
                        relatedVideoList?.let { relatedVideoList ->
                            relateVideoView.setData(
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
        setVideoPlayer(videoDetail)

        Glide.with(this).load(videoDetail.cover.blurred).into(detailBg)

        videoInfoView.setData(videoDetail)
        videoInfoView.portrait.setOnClickListener {
            ARouter.getInstance().build(RouterPaths.PERSON_MAIN_ACTIVITY)
                .withInt(Constants.USER_ID, videoDetail.author.id!!)
                .navigation()
        }
    }

    private fun setVideoPlayer(videoDetail: VideoDetail) {
        val imageView = ImageView(this)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        Glide.with(this)
            .load(videoDetail.cover.detail)
            .into(imageView)
        videoPlayer.thumbImageView = imageView

        videoPlayer.titleTextView.visibility = View.GONE
        videoPlayer.backButton.visibility = View.GONE
        videoPlayer.fullscreenButton
            .setOnClickListener { //直接横屏
                orientationUtils!!.resolveByClick()
                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                videoPlayer.startWindowFullscreen(this, true, true)
            }

        orientationUtils = OrientationUtils(this, videoPlayer)
        orientationUtils!!.isEnable = false

        val gsyVideoOption = GSYVideoOptionBuilder()
        gsyVideoOption.setThumbImageView(imageView)
            .setIsTouchWiget(true)
            .setRotateViewAuto(false)
            .setLockLand(false)
            .setAutoFullWithSize(false)
            .setShowFullAnimation(false)
            .setNeedLockFull(true)
            .setUrl(videoDetail.playUrl)
            .setCacheWithPlay(false)
            .setVideoTitle(videoDetail.title)
            .setVideoAllCallBack(object : GSYSampleCallBack() {
                override fun onPrepared(url: String, vararg objects: Any) {
                    super.onPrepared(url, *objects)
                    //开始播放了才能旋转和全屏
                    orientationUtils!!.isEnable = true
                    isPlay = true
                }

                override fun onQuitFullscreen(
                    url: String,
                    vararg objects: Any
                ) {
                    super.onQuitFullscreen(url, *objects)
                    if (orientationUtils != null) {
                        orientationUtils!!.backToProtVideo()
                    }
                }
            }).setLockClickListener { _, lock ->
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils!!.isEnable = !lock
                }
            }.build(videoPlayer)

        videoPlayer.startPlayLogic()
    }

    override fun onGetVideoDetailError() {
    }

    override fun onGetVideoRelatedSuccess(relatedVideoList: BaseListData<RelatedVideo>) {
        this.relatedVideoList = relatedVideoList
        relateVideoView.setData(relatedVideoList, false)
    }

    override fun onGetVideoRelatedError() {
    }

    override fun onBackPressed() {
        if (orientationUtils != null) {
            orientationUtils!!.backToProtVideo()
        }
        if (GSYVideoManager.backFromWindowFull(this)) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        videoPlayer.currentPlayer.onVideoPause()
        super.onPause()
        isPause = true
    }

    override fun onResume() {
        videoPlayer.currentPlayer.onVideoResume(false)
        super.onResume()
        isPause = false
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isPlay) {
            videoPlayer.currentPlayer.release()
        }
        if (orientationUtils != null) orientationUtils!!.releaseListener()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            videoPlayer.onConfigurationChanged(this, newConfig, orientationUtils, true, true)
        }
    }
}