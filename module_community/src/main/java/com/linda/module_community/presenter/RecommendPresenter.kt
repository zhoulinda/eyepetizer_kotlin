package com.linda.module_community.presenter

import com.linda.module_community.contract.RecommendContract
import com.linda.module_community.model.RecommendModel
import com.linda.module_community.ui.RecommendFragment

/**
 * 描述 :     社区->推荐presenter
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/12
 */
class RecommendPresenter(private val mView: RecommendFragment) : RecommendContract.Presenter {

    private var nextPageUrl: String? = null

    private val recommendModel: RecommendModel by lazy {
        RecommendModel()
    }

    override fun getRecommendData() {
        recommendModel.getRecommendData()
            .subscribe({
                mView.onGetRecommendDataSuccess(it)
                this.nextPageUrl = it.nextPageUrl
                mView.finishRefresh()
            }, {
                mView.onGetRecommendDataError()
            })
    }

    override fun getMoreRecommendData() {
        nextPageUrl?.let { it ->
            recommendModel.getMoreRecommendData(it)
                .subscribe({
                    mView.onGetMoreRecommendDataSuccess(it)
                    this.nextPageUrl = it.nextPageUrl
                    mView.finishRefresh()
                }, {
                    mView.onGetMoreRecommendDataError()
                })
        }
    }

    override fun onDestoryPresenter() {

    }
}