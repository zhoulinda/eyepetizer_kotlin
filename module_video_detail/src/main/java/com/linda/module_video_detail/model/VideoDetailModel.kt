package com.linda.module_video_detail.model

import com.linda.module_base.bean.BaseListData
import com.linda.module_base.bean.RelatedVideo
import com.linda.module_base.net.RetrofitManager
import com.linda.module_base.net.rx.RxScheduler
import com.linda.module_base.bean.VideoDetail
import com.linda.module_video_detail.contract.VideoDetailContract
import io.reactivex.rxjava3.core.Flowable

/**
 * 描述 :     视频详情model
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/8
 */
class VideoDetailModel : VideoDetailContract.Model {

    override fun getVideoDetail(videoId: Int, resourceType: String): Flowable<VideoDetail> {
        return RetrofitManager.service.getVideoDetailData(videoId, resourceType)
            .compose(RxScheduler.io_main())
    }

    override fun getVideoRelated(id: Int): Flowable<BaseListData<RelatedVideo>> {
        return RetrofitManager.service.getRelatedVideoData(id)
            .compose(RxScheduler.io_main())
    }
}