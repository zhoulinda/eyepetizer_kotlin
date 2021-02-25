package com.linda.module_mine.presenter

import com.linda.module_base.bean.mine.PersonMainData
import com.linda.module_mine.contract.PersonHomeContract
import com.linda.module_mine.model.PersonHomeModel
import com.linda.module_mine.ui.PersonHomeFragment

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/5
 */
class PersonHomePresenter(private val view: PersonHomeFragment) :
    PersonHomeContract.Presenter {

    private val personMainModel: PersonHomeModel by lazy {
        PersonHomeModel()
    }

    override fun getPersonHomeData(
        targetUrl: String
    ) {
        personMainModel.getPersonHomeData(
            targetUrl
        ).subscribe({
            view.onGetPersonHomeDataSuccess(it)
        }, {
            view.onGetPersonHomeDataError()
        })
    }

    override fun onDestoryPresenter() {
        personMainModel.cancelRequest()
    }
}