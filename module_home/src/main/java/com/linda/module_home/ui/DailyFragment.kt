package com.linda.module_home.ui

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
import com.linda.module_base.ui.BaseFragmentV2
import com.linda.module_home.R
import com.linda.module_home.databinding.HomeFragmentDailyBinding
import com.linda.module_home.model.DailyViewModel
import com.linda.module_home.model.DiscoverViewModel
import com.linda.module_home.repository.DailyRepository
import com.linda.module_home.repository.DiscoverRepository
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
class DailyFragment : BaseFragmentV2<HomeFragmentDailyBinding>(R.layout.home_fragment_daily),
    OnRefreshLoadMoreListener {

    private val dailyAdapter by lazy { BaseCardAdapter() }
    private val dailyViewModel by lazy { DailyViewModel(DailyRepository()) }

    override fun initView() {
        binding?.viewModel = dailyViewModel.apply {
            data.observe(viewLifecycleOwner, Observer { it ->
                if (it.isRefresh) dailyAdapter.setData(it.items) else dailyAdapter.addData(it.items)
                smartRefreshLayout.let {
                    it.finishRefresh()
                    it.finishLoadMore()
                }
            })
        }

        smartRefreshLayout.let {
            it.setEnableLoadMore(true)
            it.setEnableOverScrollBounce(false)
            it.setOnRefreshLoadMoreListener(this)
        }

        recyclerView.let {
            it.layoutManager = LinearLayoutManager(mContext)
            it.adapter = dailyAdapter.apply {
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
        dailyViewModel.getDailyData()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        dailyViewModel.getDailyData()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        dailyViewModel.getMoreDailyData()
    }
}