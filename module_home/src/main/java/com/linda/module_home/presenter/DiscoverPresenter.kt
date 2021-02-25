package com.linda.module_home.presenter

import com.linda.module_home.contract.DiscoverContract
import com.linda.module_home.model.DiscoverModel

/**
 * 描述 :    首页->发现Presenter
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/4
 */
class DiscoverPresenter(private val mView: DiscoverContract.View) : DiscoverContract.Presenter {

    private var nextPageUrl: String? = null

    private val discoverModel: DiscoverModel by lazy {
        DiscoverModel()
    }

    override fun getDiscoverData() {
        discoverModel.getDiscoverData()
            .subscribe({
                mView.onGetDiscoverDataSuccess(it)
                mView.finishRefresh()
            }, {
                mView.onGetDiscoverDataError()
            })
    }

    override fun onDestoryPresenter() {

    }
}