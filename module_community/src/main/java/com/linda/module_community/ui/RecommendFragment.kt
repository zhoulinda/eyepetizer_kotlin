package com.linda.module_community.ui

import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.linda.module_base.adapter.BaseCardAdapter
import com.linda.module_base.bean.BaseListData
import com.linda.module_base.bean.ItemData
import com.linda.module_base.constants.Constants
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.listener.OnMultiViewClickListener
import com.linda.module_base.ui.BaseFragment
import com.linda.module_base.view.StaggeredGridItemDecoration
import com.linda.module_community.R
import com.linda.module_community.contract.RecommendContract
import com.linda.module_community.presenter.RecommendPresenter
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.community_fragment_recommend.*

/**
 * 描述 :     社区->推荐Fragment
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/12
 */
@Route(path = RouterPaths.RECOMMEND_FRAGMENT)
class RecommendFragment : BaseFragment(R.layout.community_fragment_recommend),
    RecommendContract.View, OnRefreshLoadMoreListener {

    private val recommendPresenter by lazy { RecommendPresenter(this) }
    private val recommendAdapter by lazy { BaseCardAdapter() }

    override fun initView() {
        smartRefreshLayout.run {
            setEnableLoadMore(true)
            setEnableOverScrollBounce(false)
            setOnRefreshLoadMoreListener(this@RecommendFragment)
        }

        recyclerView.run {
            layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL).apply {
                    StaggeredGridLayoutManager.GAP_HANDLING_NONE
                }
            addItemDecoration(StaggeredGridItemDecoration(10, 10, 2))
            adapter = recommendAdapter.apply {
                setHasStableIds(true)
                setOnMultiViewClickListener(object : OnMultiViewClickListener<ItemData> {
                    override fun onViewClick(position: Int, view: View, data: ItemData, type: Int) {
                        when (val resourceType = data.content?.data?.resourceType) {
                            Constants.VIDEO ->
                                ARouter.getInstance()
                                    .build(RouterPaths.DETAIL_VIDEO_DETAIL_ACTIVITY)
                                    .withInt(Constants.VIDEO_ID, data.header?.id!!)
                                    .withString(Constants.RESOURCE_TYPE, resourceType)
                                    .navigation()
                            else ->
                                ARouter.getInstance()
                                    .build(RouterPaths.DETAIL_BROWSE_PICTURE_ACTIVITY)
                                    .withInt(Constants.PICTURE_ID, data.header?.id!!)
                                    .withString(Constants.RESOURCE_TYPE, resourceType)
                                    .navigation()
                        }
                    }
                })
            }
        }
    }

    override fun initData() {
        recommendPresenter.getRecommendData()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        recommendPresenter.getRecommendData()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        recommendPresenter.getMoreRecommendData()
    }

    override fun onGetRecommendDataSuccess(baseListData: BaseListData<ItemData>) {
        recommendAdapter.setData(baseListData.itemList)
    }

    override fun onGetMoreRecommendDataSuccess(baseListData: BaseListData<ItemData>) {
        recommendAdapter.insertData(baseListData.itemList)
    }

    override fun onGetRecommendDataError() {

    }

    override fun onGetMoreRecommendDataError() {

    }

    fun finishRefresh() {
        smartRefreshLayout.run {
            finishRefresh()
            finishLoadMore()
        }
    }
}