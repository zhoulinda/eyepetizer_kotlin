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
    suspend fun getDiscoverData(): BaseListData<ItemData>


    /**
     * 首页->日报
     */
    @GET("api/v5/index/tab/feed")
    suspend fun getDailyData(): BaseListData<ItemData>


    /**
     * 首页->日报 下一页数据
     */
    @GET
    suspend fun getMoreDailyData(@Url url: String): BaseListData<ItemData>


    /**
     * 视频详情
     */
    @GET("api/v2/video/{videoId}")
    suspend fun getVideoDetailData(
        @Path("videoId") videoId: Int,
        @Query("resourceType") resourceType: String
    ): VideoDetail


    /**
     * 相关视频
     */
    @GET("api/v4/video/related")
    suspend fun getRelatedVideoData(@Query("id") videoId: Int): BaseListData<RelatedVideo>


    /**
     * 视频播放地址
     */
    @GET("api/v1/playUrl")
    fun playUrl(@Query("vid") vid: Int): Flowable<Any>


    /**
     * 社区推荐
     */
    @GET("api/v7/community/tab/rec")
    suspend fun getCommunityRecommendData(): BaseListData<ItemData>


    /**
     * 社区推荐更多
     */
    @GET
    suspend fun getMoreCommunityRecommendData(@Url url: String): BaseListData<ItemData>


    /**
     * 社区关注
     */
    @GET("api/v6/community/tab/follow")
    suspend fun getCommunityAttentionData(): BaseListData<ItemData>


    /**
     * 社区关注更多
     */
    @GET
    suspend fun getMoreCommunityAttentionData(@Url url: String): BaseListData<ItemData>


    /**
     * 个人主页
     */
    @GET("api/v5/userInfo/tab")
    suspend fun getPersonMainData(
        @Query("id") id: Int,
        @Query("userType") userType: String
    ): PersonMainData


    @GET
    suspend fun getPersonHomeData(
        @Url url: String
    ): BaseListData<ItemData>


    /**
     * 注册
     */
    @FormUrlEncoded
    @POST
    suspend fun register(
        @Url url: String,
        @Field("username") phone: String,
        @Field("code") code: String,
        @Field("password") password: String
    ): Login


    /**
     * 获取验证码
     */
    @FormUrlEncoded
    @POST
    fun getVerificationCode(
        @Url url: String,
        @Field("telephone") phone: String,
        @Field("type") type: String
    ): VerificationCode


    /**
     * 登录
     */
    @FormUrlEncoded
    @POST
    suspend fun login(
        @Url url: String,
        @Field("username") phone: String,
        @Field("password") password: String
    ): Login

}