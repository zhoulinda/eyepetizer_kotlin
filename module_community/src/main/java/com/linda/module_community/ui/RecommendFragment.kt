package com.linda.module_community.ui

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.linda.module_base.adapter.BaseCardAdapter
import com.linda.module_base.bean.ItemData
import com.linda.module_base.constants.Constants
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.listener.OnMultiViewClickListener
import com.linda.module_base.ui.BaseFragmentV2
import com.linda.module_base.view.StaggeredGridItemDecoration
import com.linda.module_community.R
import com.linda.module_community.databinding.CommunityFragmentRecommendBinding
import com.linda.module_community.model.RecommendViewModel
import com.linda.module_home.repository.RecommendRepository
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
class RecommendFragment :
    BaseFragmentV2<CommunityFragmentRecommendBinding>(R.layout.community_fragment_recommend),
    OnRefreshLoadMoreListener {

    private val recommendAdapter by lazy { BaseCardAdapter() }
    private val recommendViewModel by lazy { RecommendViewModel(RecommendRepository()) }

    override fun initView() {
        binding?.viewModel = recommendViewModel.apply {
            data.observe(viewLifecycleOwner, Observer { it ->
                if (it.isRefresh) recommendAdapter.setData(it.items)
                else recommendAdapter.insertData(it.items)
                smartRefreshLayout.run {
                    finishRefresh()
                    finishLoadMore()
                }
            })
        }
        smartRefreshLayout.run {
            setEnableLoadMore(true)
            setEnableOverScrollBounce(false)
            setOnRefreshLoadMoreListener(this@RecommendFragment)
        }

        recyclerView.run {
            layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL).apply {
                    gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
                }
            addItemDecoration(StaggeredGridItemDecoration(10, 10, 2))
            adapter = recommendAdapter.apply {
                setHasStableIds(true)
                setOnMultiViewClickListener(object : OnMultiViewClickListener<ItemData> {
                    override fun onViewClick(position: Int, view: View, data: ItemData, type: Int) {
                        val id = data.header?.id
                        val resourceType = data.content?.data?.resourceType
                        data.header?.id?.let {
                            if (data.content?.data?.resourceType == Constants.VIDEO) {
                                ARouter.getInstance()
                                    .build(RouterPaths.DETAIL_VIDEO_DETAIL_ACTIVITY)
                                    .withInt(Constants.VIDEO_ID, id!!)
                                    .withString(Constants.RESOURCE_TYPE, resourceType)
                                    .navigation()
                                return
                            }
                            if (data.content?.data?.resourceType == Constants.UGC_PICTURE) {
                                ARouter.getInstance()
                                    .build(RouterPaths.DETAIL_BROWSE_PICTURE_ACTIVITY)
                                    .withInt(Constants.PICTURE_ID, it)
                                    .withString(Constants.RESOURCE_TYPE, resourceType)
                                    .navigation()
                                return
                            }
                        }
                    }
                })
            }
        }
    }

    override fun initData() {
        recommendViewModel.getCommunityRecommendData()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        recommendViewModel.getCommunityRecommendData()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        recommendViewModel.getMoreCommunityRecommendData()
    }
}