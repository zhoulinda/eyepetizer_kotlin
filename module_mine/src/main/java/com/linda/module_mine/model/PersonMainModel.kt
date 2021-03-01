package com.linda.module_mine.model

import com.linda.module_base.bean.mine.PersonMainData
import com.linda.module_base.net.RetrofitManager
import com.linda.lib_net.rx.RxScheduler
import com.linda.module_mine.contract.PersonMainContract
import io.reactivex.rxjava3.core.Flowable

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/5
 */
class PersonMainModel : PersonMainContract.Model {

    override fun getPersonMainData(id: Int, userType: String): Flowable<PersonMainData> {
        return RetrofitManager.service.getPersonMainData(id, userType)
            .compose(RxScheduler.io_main())
    }

    override fun cancelRequest() {
    }
}