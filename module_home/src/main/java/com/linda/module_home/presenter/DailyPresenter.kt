package com.linda.module_home.presenter

import com.linda.module_home.contract.DailyContract
import com.linda.module_home.contract.DiscoverContract
import com.linda.module_home.model.DailyModel
import com.linda.module_home.ui.DailyFragment

/**
 * 描述 :     日报Presenter
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/6
 */
class DailyPresenter(private val view: DailyContract.View) : DailyContract.Presenter {

    private var nextPageUrl: String? = null

    private val dailyModel: DailyModel by lazy {
        DailyModel()
    }

    override fun getDailyData() {
        dailyModel.getDailyData()
            .subscribe({
                view.onGetDailyDataSuccess(it)
                this.nextPageUrl = it.nextPageUrl
                view.finishRefresh()
            }, {
                view.onGetDailyDataError()
            })
    }

    override fun getMoreDailyData() {
        nextPageUrl?.let { it ->
            dailyModel.getDailyMoreData(it)
                .subscribe({
                    view.onGetDailyMoreDataSuccess(it)
                    this.nextPageUrl = it.nextPageUrl
                    view.finishRefresh()
                }, {
                    view.onGetDailyMoreDataError()
                })
        }
    }

    override fun onDestoryPresenter() {

    }
}