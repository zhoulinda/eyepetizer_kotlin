package com.linda.module_home.ui

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
import com.linda.module_home.R
import com.linda.module_home.contract.DiscoverContract
import com.linda.module_home.presenter.DiscoverPresenter
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.home_fragment_daily.*
import kotlinx.android.synthetic.main.home_fragment_discover.recyclerView

/**
 * 描述 :     发现Fragment
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/1
 */

@Route(path = RouterPaths.DISCOVER_FRAGMENT)
class DiscoverFragment : BaseFragment(), DiscoverContract.View, OnRefreshListener {

    private var adapter: BaseCardAdapter? = null
    private var discoverPresenter: DiscoverPresenter? = null

    override fun getLayoutResId(): Int {
        return R.layout.home_fragment_discover
    }

    override fun initView() {
        smartRefreshLayout.setEnableLoadMore(false)
        smartRefreshLayout.setOnRefreshListener(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = BaseCardAdapter()
        recyclerView.adapter = adapter
        adapter?.setOnMultiViewClickListener(object : OnMultiViewClickListener<ItemData> {
            override fun onViewClick(position: Int, view: View, data: ItemData, type: Int) {
                ARouter.getInstance().build(RouterPaths.DETAIL_VIDEO_DETAIL_ACTIVITY)
                    .withInt(Constants.VIDEO_ID, data.id!!)
                    .withString(Constants.RESOURCE_TYPE, data.resourceType)
                    .navigation()
            }
        })
    }

    override fun initData() {
        discoverPresenter = DiscoverPresenter(this)
        discoverPresenter?.getDiscoverData()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        discoverPresenter?.getDiscoverData()
    }

    override fun onGetDiscoverDataSuccess(baseListData: BaseListData<ItemData>) {
        adapter?.setData(baseListData.itemList)
    }

    override fun onGetDiscoverDataError() {
    }

    override fun finishRefresh() {
        smartRefreshLayout.finishRefresh()
        smartRefreshLayout.finishLoadMore()
    }
}