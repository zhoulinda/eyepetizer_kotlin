package com.linda.module_video_detail.model

import com.linda.module_base.bean.VideoDetail
import com.linda.module_base.net.RetrofitManager
import com.linda.module_base.net.rx.RxScheduler
import com.linda.module_video_detail.contract.PictureDetailContract
import io.reactivex.rxjava3.core.Flowable

/**
 * 描述 :     视频详情model
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/8
 */
class PictureDetailModel : PictureDetailContract.Model {
    override fun getPictureDetail(pictureId: Int, resourceType: String): Flowable<VideoDetail> {
        return RetrofitManager.service.getVideoDetailData(pictureId, resourceType)
            .compose(RxScheduler.io_main())
    }
}