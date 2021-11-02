package com.linda.module_video_detail.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.linda.lib_common.CommonApplication
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

    private var originRelatedVideos: MutableList<VideoItem<RelatedVideo>> = ArrayList()

    var relatedVideos: MutableLiveData<List<VideoItem<RelatedVideo>>> =
        MutableLiveData()

    val isShowMore: MutableLiveData<Boolean> = MutableLiveData()

    val totalIndex: MutableLiveData<Int> = MutableLiveData()

    val currentIndex: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = 1 }


    fun getVideoDetail(videoId: Int, resourceType: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val videoDetail = withContext(Dispatchers.IO) {
                videoDetailRepository.getVideoDetail(
                    videoId,
                    resourceType
                )
            }
            totalIndex.value = videoDetail.urls?.size
            detailData.value = videoDetail
        }
    }

    fun getVideoRelated(videoId: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            val videoRelated = withContext(Dispatchers.IO) {
                videoDetailRepository.getVideoRelated(videoId)
            }
            originRelatedVideos = videoRelated.itemList
            if (originRelatedVideos.size > 5) {
                isShowMore.value = true
                relatedVideos.value = originRelatedVideos.subList(
                    0,
                    6
                )
            } else {
                isShowMore.value = false
                relatedVideos.value = originRelatedVideos
            }
        }
    }

    fun setCurrentIndex(index: Int) {
        currentIndex.value = index
    }

    fun showAllRelateVideos() {
        isShowMore.value = false
        relatedVideos.value = originRelatedVideos
    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String?) {
            if (!url.isNullOrEmpty()) {
                Glide.with(CommonApplication.getContext())
                    .load(url)
                    .apply(RequestOptions.bitmapTransform(CircleCrop()))
                    .into(view)
            }
        }

        @JvmStatic
        @BindingAdapter("bgImage")
        fun loadBgImage(view: ImageView, url: String?) {
            if (!url.isNullOrEmpty()) {
                Glide.with(CommonApplication.getContext())
                    .load(url)
                    .into(view)
            }
        }
    }
}