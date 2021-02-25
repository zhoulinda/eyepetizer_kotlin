package com.linda.module_video_detail.contract

import com.linda.module_base.IModel
import com.linda.module_base.IPresenter
import com.linda.module_base.IView
import com.linda.module_base.bean.BaseListData
import com.linda.module_base.bean.RelatedVideo
import com.linda.module_base.bean.VideoDetail
import io.reactivex.rxjava3.core.Flowable

/**
 * 描述 :     视频详情contract
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/8
 */
interface VideoDetailContract {

    interface View : IView {
        fun onGetVideoDetailSuccess(videoDetail: VideoDetail)

        fun onGetVideoDetailError()

        fun onGetVideoRelatedSuccess(relatedVideoList: BaseListData<RelatedVideo>)

        fun onGetVideoRelatedError()
    }

    interface Presenter : IPresenter {
        fun getVideoDetail(videoId: Int, resourceType: String)

        fun getVideoRelated(id: Int)
    }

    interface Model : IModel {
        fun getVideoDetail(videoId: Int, resourceType: String): Flowable<VideoDetail>

        fun getVideoRelated(id: Int): Flowable<BaseListData<RelatedVideo>>
    }

}