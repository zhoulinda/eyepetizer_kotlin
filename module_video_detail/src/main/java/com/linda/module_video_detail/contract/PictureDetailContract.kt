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
interface PictureDetailContract {

    interface View : IView {
        fun onGetPictureDetailSuccess(videoDetail: VideoDetail)

        fun onGetPictureDetailError()
    }

    interface Presenter : IPresenter {
        fun getPictureDetail(pictureId: Int, resourceType: String)
    }

    interface Model : IModel {
        fun getPictureDetail(pictureId: Int, resourceType: String): Flowable<VideoDetail>
    }

}