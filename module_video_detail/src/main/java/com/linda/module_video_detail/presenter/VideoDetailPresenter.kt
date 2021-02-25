package com.linda.module_video_detail.presenter

import com.linda.module_video_detail.contract.VideoDetailContract
import com.linda.module_video_detail.model.VideoDetailModel

/**
 * 描述 :     视频详情Presenter
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/8
 */
class VideoDetailPresenter(private val mView: VideoDetailContract.View) :
    VideoDetailContract.Presenter {

    private val videoDetailModel: VideoDetailModel by lazy {
        VideoDetailModel()
    }

    override fun getVideoDetail(videoId: Int, resourceType: String) {
        videoDetailModel.getVideoDetail(videoId, resourceType)
            .subscribe({
                mView.onGetVideoDetailSuccess(it)
            }, {
                mView.onGetVideoDetailError()
            })
    }

    override fun getVideoRelated(id: Int) {
        videoDetailModel.getVideoRelated(id)
            .subscribe({
                mView.onGetVideoRelatedSuccess(it)
            }, {
                mView.onGetVideoRelatedError()
            })
    }

    override fun onDestoryPresenter() {
    }
}