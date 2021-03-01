package com.linda.module_home.model

import com.linda.module_base.bean.BaseListData
import com.linda.module_base.bean.ItemData
import com.linda.module_base.net.RetrofitManager
import com.linda.lib_net.rx.RxScheduler
import com.linda.module_home.contract.DiscoverContract
import io.reactivex.rxjava3.core.Flowable

/**
 * 描述 :     首页->发现model
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/4
 */
class DiscoverModel : DiscoverContract.Model {

    override fun getDiscoverData(): Flowable<BaseListData<ItemData>> {
        return RetrofitManager.service.getDiscoverData()
            .compose(RxScheduler.io_main())
    }
}