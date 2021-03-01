package com.linda.module_home.model

import com.linda.module_base.bean.BaseListData
import com.linda.module_base.bean.ItemData
import com.linda.module_base.net.RetrofitManager
import com.linda.lib_net.rx.RxScheduler
import com.linda.module_home.contract.DailyContract
import io.reactivex.rxjava3.core.Flowable

/**
 * 描述 :     首页->日报model
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/6
 */
class DailyModel : DailyContract.Model {

    override fun getDailyData(): Flowable<BaseListData<ItemData>> {
        return RetrofitManager.service.getDailyData()
            .compose(RxScheduler.io_main())
    }

    override fun getDailyMoreData(url: String): Flowable<BaseListData<ItemData>> {
        return RetrofitManager.service.getMoreDailyData(url)
            .compose(RxScheduler.io_main())
    }

    override fun cancelRequest() {

    }
}