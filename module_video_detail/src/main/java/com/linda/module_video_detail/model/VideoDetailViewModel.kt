package com.linda.module_video_detail.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.linda.module_base.bean.ItemData
import com.linda.module_base.bean.RelatedVideo
import com.linda.module_base.bean.VideoDetail
import com.linda.module_base.bean.VideoItem
import com.linda.module_video_detail.repository.VideoDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
Describe:
Created by linda on 2021/10/13
Email:zhoulinda@songguo7.com
 */
class VideoDetailViewModel(private val videoDetailRepository: VideoDetailRepository) : ViewModel() {

    val detailData: MutableLiveData<VideoDetail> = MutableLiveData()

    val relatedVideos: MutableLiveData<RelatedVideosUiModel> =
        MutableLiveData()

    private var originRelatedVideos: MutableList<VideoItem<RelatedVideo>> = ArrayList()

    fun getVideoDetail(videoId: Int, resourceType: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val videoDetail = withContext(Dispatchers.IO) {
                videoDetailRepository.getVideoDetail(
                    videoId,
                    resourceType
                )
            }
            detailData.value = videoDetail
        }
    }

    fun getVideoRelated(videoId: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            val videoRelated = withContext(Dispatchers.IO) {
                videoDetailRepository.getVideoRelated(videoId)
            }
            originRelatedVideos = videoRelated.itemList
            relatedVideos.value = RelatedVideosUiModel(originRelatedVideos, false)
        }
    }

    fun showAllVideos() {
        relatedVideos.value = RelatedVideosUiModel(originRelatedVideos, true)
    }

    data class RelatedVideosUiModel(
        val items: MutableList<VideoItem<RelatedVideo>>,
        val isShowAll: Boolean
    )
}