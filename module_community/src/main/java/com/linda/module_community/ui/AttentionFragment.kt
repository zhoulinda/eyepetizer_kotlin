package com.linda.module_community.ui

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.linda.module_base.adapter.BaseCardAdapter
import com.linda.module_base.bean.BaseListData
import com.linda.module_base.bean.ItemData
import com.linda.module_base.constants.Constants
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.listener.OnMultiViewClickListener
import com.linda.module_base.ui.BaseFragment
import com.linda.module_community.R
import com.linda.module_community.contract.AttentionContract
import com.linda.module_community.presenter.AttentionPresenter
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
class AttentionFragment : BaseFragment(R.layout.community_fragment_attention),
    AttentionContract.View, OnRefreshLoadMoreListener {

    private val attentionPresenter by lazy { AttentionPresenter(this) }
    private val attentionAdapter by lazy { BaseCardAdapter() }

    override fun initView() {
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
                        if (type == BaseCardAdapter.ITEM_TYPE_AUTO_PLAY_FOLLOW_CARD) {
                            if (view.id == R.id.portrait) {
                                ARouter.getInstance().build(RouterPaths.PERSON_MAIN_ACTIVITY)
                                    .withInt(Constants.USER_ID, data.content?.data?.author?.id!!)
                                    .navigation()
                            } else {
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
                    }
                })
            }
        }
    }

    override fun initData() {
        attentionPresenter.getAttentionData()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        attentionPresenter.getAttentionData()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        attentionPresenter.getMoreAttentionData()
    }

    override fun onGetAttentionDataSuccess(recommends: BaseListData<ItemData>) {
        attentionAdapter.setData(recommends.itemList)
    }

    override fun onGetAttentionDataError() {
    }

    override fun onGetMoreAttentionDataSuccess(recommends: BaseListData<ItemData>) {
        attentionAdapter.addData(recommends.itemList)
    }

    override fun onGetMoreAttentionDataError() {
    }

    override fun finishRefresh() {
        smartRefreshLayout.run {
            finishRefresh()
            finishLoadMore()
        }
    }
}