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
class AttentionFragment : BaseFragment(), AttentionContract.View, OnRefreshLoadMoreListener {

    private var presenter: AttentionPresenter? = null
    private var adapter: BaseCardAdapter? = null

    override fun getLayoutResId(): Int {
        return R.layout.community_fragment_attention
    }

    override fun initView() {
        smartRefreshLayout.setEnableLoadMore(true)
        smartRefreshLayout.setOnRefreshLoadMoreListener(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = BaseCardAdapter()
        recyclerView.adapter = adapter
        adapter?.setOnMultiViewClickListener(object :
            OnMultiViewClickListener<ItemData> {
            override fun onViewClick(position: Int, view: View, data: ItemData, type: Int) {
                if (type == BaseCardAdapter.ITEM_TYPE_AUTO_PLAY_FOLLOW_CARD) {
                    if (view.id == R.id.portrait) {
                        ARouter.getInstance().build(RouterPaths.PERSON_MAIN_ACTIVITY)
                            .withInt(Constants.USER_ID, data.content?.data?.author?.id!!)
                            .navigation()
                    } else {
                        ARouter.getInstance().build(RouterPaths.DETAIL_VIDEO_DETAIL_ACTIVITY)
                            .withInt(Constants.VIDEO_ID, data.content?.data?.id!!)
                            .withString(Constants.RESOURCE_TYPE, data.content?.data?.resourceType)
                            .navigation()
                    }
                }
            }
        })
    }

    override fun initData() {
        presenter = AttentionPresenter(this)
        presenter?.getAttentionData()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        presenter?.getAttentionData()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        presenter?.getMoreAttentionData()
    }

    override fun onGetAttentionDataSuccess(recommends: BaseListData<ItemData>) {
        adapter?.setData(recommends.itemList)
    }

    override fun onGetAttentionDataError() {
    }

    override fun onGetMoreAttentionDataSuccess(recommends: BaseListData<ItemData>) {
        adapter?.addData(recommends.itemList)
    }

    override fun onGetMoreAttentionDataError() {
    }

    override fun finishRefresh() {
        smartRefreshLayout.finishRefresh()
        smartRefreshLayout.finishLoadMore()
    }
}