package com.linda.module_mine.presenter

import com.linda.module_mine.ui.PersonMainActivity
import com.linda.module_mine.contract.PersonMainContract
import com.linda.module_mine.model.PersonMainModel

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/5
 */
class PersonMainPresenter(private val view: PersonMainActivity) : PersonMainContract.Presenter {

    private val personMainModel: PersonMainModel by lazy {
        PersonMainModel()
    }

    override fun getPersonMainData(id: Int) {
        personMainModel.getPersonMainData(id, if (id.toString().length > 4) "NORMAL" else "PGC")
            .subscribe({
                view.onGetPersonMainDataSuccess(it)
            }, {
                view.onGetDailyDataError()
            })
    }

    override fun onDestoryPresenter() {
        personMainModel.cancelRequest()
    }
}