package com.linda.module_video_detail.repository

import com.linda.module_base.bean.BaseListData
import com.linda.module_base.bean.RelatedVideo
import com.linda.module_base.bean.VideoDetail
import com.linda.module_base.net.RetrofitManager

/**
Describe:
Created by linda on 2021/10/13
Email:zhoulinda@songguo7.com
 */
class VideoDetailRepository {

    suspend fun getVideoDetail(videoId: Int, resourceType: String): VideoDetail {
        return RetrofitManager.serviceV2.getVideoDetailData(videoId, resourceType)
    }

    suspend fun getVideoRelated(videoId: Int): BaseListData<RelatedVideo> {
        return RetrofitManager.serviceV2.getRelatedVideoData(videoId)
    }
}