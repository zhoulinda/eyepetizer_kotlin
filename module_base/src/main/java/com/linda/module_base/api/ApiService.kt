package com.linda.module_base.api

import com.linda.module_base.bean.*
import com.linda.module_base.bean.mine.PersonMainData
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.*

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/1
 */
interface ApiService {

    /**
     * 首页->发现
     */
    @GET("api/v7/index/tab/discovery")
    fun getDiscoverData(): Flowable<BaseListData<ItemData>>

    /**
     * 首页->日报
     */
    @GET("api/v5/index/tab/feed")
    fun getDailyData(): Flowable<BaseListData<ItemData>>

    /**
     * 首页->日报 下一页数据
     */
    @GET
    fun getMoreDailyData(@Url url: String): Flowable<BaseListData<ItemData>>

    /**
     * 视频详情
     */
    @GET("api/v2/video/{videoId}")
    fun getVideoDetailData(
        @Path("videoId") videoId: Int,
        @Query("resourceType") resourceType: String
    ): Flowable<VideoDetail>

    /**
     * 相关视频
     */
    @GET("api/v4/video/related")
    fun getRelatedVideoData(@Query("id") videoId: Int): Flowable<BaseListData<RelatedVideo>>

    /**
     * 视频播放地址
     */
    @GET("api/v1/playUrl")
    fun playUrl(@Query("vid") vid: Int): Flowable<Any>

    /**
     * 社区推荐
     */
    @GET("api/v7/community/tab/rec")
    fun getCommunityRecommendData(): Flowable<BaseListData<ItemData>>

    /**
     * 社区推荐更多
     */
    @GET
    fun getMoreCommunityRecommendData(@Url url: String): Flowable<BaseListData<ItemData>>

    /**
     * 社区关注
     */
    @GET("api/v6/community/tab/follow")
    fun getCommunityAttentionData(): Flowable<BaseListData<ItemData>>

    /**
     * 社区关注更多
     */
    @GET
    fun getMoreCommunityAttentionData(@Url url: String): Flowable<BaseListData<ItemData>>

    /**
     * 个人主页
     */
    @GET("api/v5/userInfo/tab")
    fun getPersonMainData(
        @Query("id") id: Int,
        @Query("userType") userType: String
    ): Flowable<PersonMainData>

    @GET
    fun getPersonHomeData(
        @Url url: String
    ): Flowable<BaseListData<ItemData>>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST
    fun register(
        @Url url: String,
        @Field("username") phone: String,
        @Field("code") code: String,
        @Field("password") password: String
    ): Flowable<Login>

    /**
     * 获取验证码
     */
    @FormUrlEncoded
    @POST
    fun getVerificationCode(
        @Url url: String,
        @Field("telephone") phone: String,
        @Field("type") type: String
    ): Flowable<VerificationCode>

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST
    fun login(
        @Url url: String,
        @Field("username") phone: String,
        @Field("password") password: String
    ): Flowable<Login>

}