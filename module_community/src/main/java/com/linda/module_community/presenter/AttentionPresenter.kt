package com.linda.module_community.presenter

import com.linda.module_community.contract.AttentionContract
import com.linda.module_community.contract.RecommendContract
import com.linda.module_community.model.AttentionModel
import com.linda.module_community.model.RecommendModel
import com.linda.module_community.ui.RecommendFragment

/**
 * 描述 :     社区->推荐presenter
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/12
 */
class AttentionPresenter(private val mView: AttentionContract.View) : AttentionContract.Presenter {

    private var nextPageUrl: String? = null

    private val recommendModel: AttentionModel by lazy {
        AttentionModel()
    }

    override fun getAttentionData() {
        recommendModel.getAttentionData()
            .subscribe({
                mView.onGetAttentionDataSuccess(it)
                this.nextPageUrl = it.nextPageUrl
                mView.finishRefresh()
            }, {
                mView.onGetAttentionDataError()
            })
    }

    override fun getMoreAttentionData() {
        nextPageUrl?.let { it ->
            recommendModel.getMoreAttentionData(it)
                .subscribe({
                    mView.onGetMoreAttentionDataSuccess(it)
                    this.nextPageUrl = it.nextPageUrl
                    mView.finishRefresh()
                }, {
                    mView.onGetMoreAttentionDataError()
                })
        }
    }

    override fun onDestoryPresenter() {

    }
}