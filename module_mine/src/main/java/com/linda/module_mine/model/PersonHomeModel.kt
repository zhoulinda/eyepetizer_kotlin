package com.linda.module_mine.model

import com.linda.module_base.bean.BaseListData
import com.linda.module_base.bean.ItemData
import com.linda.module_base.net.RetrofitManager
import com.linda.lib_net.rx.RxScheduler
import com.linda.module_mine.contract.PersonHomeContract
import io.reactivex.rxjava3.core.Flowable

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/5
 */
class PersonHomeModel : PersonHomeContract.Model {

    override fun getPersonHomeData(
        targetUrl: String
    ): Flowable<BaseListData<ItemData>> {
        return RetrofitManager.service.getPersonHomeData(targetUrl)
            .compose(RxScheduler.io_main())
    }

    override fun cancelRequest() {
    }
}