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
import kotlinx.android.synthetic.main.home_fragment_daily.smartRefreshLayout
import kotlinx.android.synthetic.main.home_fragment_discover.*
import kotlinx.android.synthetic.main.home_fragment_discover.recyclerView

/**
 * 描述 :     发现Fragment
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/1
 */

@Route(path = RouterPaths.DISCOVER_FRAGMENT)
class DiscoverFragment : BaseFragment(R.layout.home_fragment_discover), DiscoverContract.View,
    OnRefreshListener {

    private val discoverAdapter by lazy { BaseCardAdapter() }
    private val discoverPresenter by lazy { DiscoverPresenter(this) }

    override fun initView() {
        smartRefreshLayout.run {
            setEnableLoadMore(false)
            setOnRefreshListener(this@DiscoverFragment)
        }

        recyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = discoverAdapter.apply {
                setOnMultiViewClickListener(object : OnMultiViewClickListener<ItemData> {
                    override fun onViewClick(position: Int, view: View, data: ItemData, type: Int) {
                        ARouter.getInstance().build(RouterPaths.DETAIL_VIDEO_DETAIL_ACTIVITY)
                            .withInt(Constants.VIDEO_ID, data.id!!)
                            .withString(Constants.RESOURCE_TYPE, data.resourceType)
                            .navigation()
                    }
                })
            }
        }
    }

    override fun initData() {
        discoverPresenter.getDiscoverData()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        discoverPresenter.getDiscoverData()
    }

    override fun onGetDiscoverDataSuccess(baseListData: BaseListData<ItemData>) {
        discoverAdapter.setData(baseListData.itemList)
    }

    override fun onGetDiscoverDataError() {
    }

    override fun finishRefresh() {
        smartRefreshLayout.run {
            finishRefresh()
            finishLoadMore()
        }
    }
}