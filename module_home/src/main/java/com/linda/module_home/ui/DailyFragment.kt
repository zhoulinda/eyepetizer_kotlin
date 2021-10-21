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
import com.linda.module_home.contract.DailyContract
import com.linda.module_home.presenter.DailyPresenter
import com.linda.module_home.presenter.DiscoverPresenter
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.home_fragment_daily.*

/**
 * 描述 :    日报Fragment
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/6
 */
@Route(path = RouterPaths.DAILY_FRAGMENT)
class DailyFragment : BaseFragment(R.layout.home_fragment_daily), DailyContract.View,
    OnRefreshLoadMoreListener {

    private val dailyAdapter by lazy { BaseCardAdapter() }
    private val dailyPresenter by lazy { DailyPresenter(this) }

    override fun initView() {
        smartRefreshLayout.run {
            setEnableLoadMore(true)
            setEnableOverScrollBounce(false)
            setOnRefreshLoadMoreListener(this@DailyFragment)
        }

        recyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = dailyAdapter.apply {
                setOnMultiViewClickListener(object :
                    OnMultiViewClickListener<ItemData> {
                    override fun onViewClick(position: Int, view: View, data: ItemData, type: Int) {
                        if (type == BaseCardAdapter.ITEM_TYPE_INFORMATION_CARD) {
                            ARouter.getInstance().build(RouterPaths.WEBVIEW_ACTIVITY)
                                .withString("url", "http://www.baidu.com")
                                .navigation()
                        } else if (type == BaseCardAdapter.ITEM_TYPE_FOLLOW_CARD) {
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
        dailyPresenter.getDailyData()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        dailyPresenter.getDailyData()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        dailyPresenter.getMoreDailyData()
    }

    override fun onGetDailyDataSuccess(itemDataList: BaseListData<ItemData>) {
        dailyAdapter.setData(itemDataList.itemList)
    }

    override fun onGetDailyMoreDataSuccess(itemDataList: BaseListData<ItemData>) {
        dailyAdapter.addData(itemDataList.itemList)
    }

    override fun onGetDailyMoreDataError() {

    }

    override fun onGetDailyDataError() {

    }

    override fun finishRefresh() {
        smartRefreshLayout.run {
            finishRefresh()
            finishLoadMore()
        }
    }
}