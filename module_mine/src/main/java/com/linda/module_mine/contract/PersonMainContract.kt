package com.linda.module_mine.contract

import com.linda.module_base.IModel
import com.linda.module_base.IPresenter
import com.linda.module_base.IView
import com.linda.module_base.bean.mine.PersonMainData
import io.reactivex.rxjava3.core.Flowable

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/5
 */
interface PersonMainContract {

    interface View : IView {

        fun onGetPersonMainDataSuccess(data: PersonMainData)

        fun onGetDailyDataError()
    }

    interface Presenter : IPresenter {

        fun getPersonMainData(id: Int)
    }

    interface Model : IModel {

        fun getPersonMainData(id: Int, userType: String): Flowable<PersonMainData>

        fun cancelRequest()
    }
}