package com.linda.module_community.ui

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.linda.module_base.adapter.BaseCardAdapter
import com.linda.module_base.bean.ItemData
import com.linda.module_base.constants.Constants
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.listener.OnMultiViewClickListener
import com.linda.module_base.ui.BaseFragment
import com.linda.module_community.R
import com.linda.module_community.databinding.CommunityFragmentAttentionBinding
import com.linda.module_community.model.AttentionViewModel
import com.linda.module_home.repository.AttentionRepository
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.community_fragment_attention.*

/**
 * 描述 :  社区 -> 关注Fragment
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/12
 */
@Route(path = RouterPaths.ATTENTION_FRAGMENT)
class AttentionFragment :
    BaseFragment<CommunityFragmentAttentionBinding>(R.layout.community_fragment_attention),
    OnRefreshLoadMoreListener {

    private val attentionAdapter: BaseCardAdapter by lazy { BaseCardAdapter() }
    private val attentionViewMode by lazy { AttentionViewModel(AttentionRepository()) }

    override fun initView() {
        attentionViewMode.run {
            data.observe(viewLifecycleOwner, Observer {
                if (it.isRefresh) attentionAdapter.setData(it.items)
                else attentionAdapter.insertData(it.items)
                smartRefreshLayout.run {
                    finishRefresh()
                    finishLoadMore()
                }
            })
        }
        smartRefreshLayout.run {
            setEnableLoadMore(true)
            setOnRefreshLoadMoreListener(this@AttentionFragment)
        }

        recyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = attentionAdapter.apply {
                setOnMultiViewClickListener(object :
                    OnMultiViewClickListener<ItemData> {
                    override fun onViewClick(position: Int, view: View, data: ItemData, type: Int) {
                        when (view.id) {
                            R.id.portrait ->
                                ARouter.getInstance().build(RouterPaths.PERSON_MAIN_ACTIVITY)
                                    .withInt(Constants.USER_ID, data.content?.data?.author?.id!!)
                                    .navigation()
                            else ->
                                ARouter.getInstance()
                                    .build(RouterPaths.DETAIL_VIDEO_DETAIL_ACTIVITY)
                                    .withInt(Constants.VIDEO_ID, data.content?.data?.id!!)
                                    .withString(
                                        Constants.RESOURCE_TYPE,
                                        data.content?.data?.resourceType
                                    )
                                    .navigation()

                        }
                    }
                })
            }
        }
    }

    override fun initData() {
        attentionViewMode.getAttentionData()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        attentionViewMode.getAttentionData()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        attentionViewMode.getMoreAttentionData()
    }
}