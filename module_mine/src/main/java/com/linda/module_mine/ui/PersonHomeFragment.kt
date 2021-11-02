package com.linda.module_mine.ui

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.linda.module_base.adapter.BaseCardAdapter
import com.linda.module_base.bean.BaseListData
import com.linda.module_base.bean.ItemData
import com.linda.module_base.bean.mine.TabData
import com.linda.module_base.constants.Constants
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.listener.OnMultiViewClickListener
import com.linda.module_base.ui.BaseFragment
import com.linda.module_mine.R
import com.linda.module_mine.contract.PersonHomeContract
import com.linda.module_mine.presenter.PersonHomePresenter
import kotlinx.android.synthetic.main.mine_fragment_person_main_sub.*

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/5
 */
@Route(path = RouterPaths.PERSON_HOME_FRAGMENT)
class PersonHomeFragment : BaseFragment(R.layout.mine_fragment_person_main_sub),
    PersonHomeContract.View {

    @JvmField
    @Autowired(name = Constants.TAB_DATA)
    var tabData: TabData? = null

    private val presenter by lazy { PersonHomePresenter(this) }
    private val personHomeAdapter by lazy { BaseCardAdapter() }

    override fun initView() {
        ARouter.getInstance().inject(this)
        recyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = personHomeAdapter.apply {
                setOnMultiViewClickListener(object :
                    OnMultiViewClickListener<ItemData> {
                    override fun onViewClick(position: Int, view: View, data: ItemData, type: Int) {
                        when (type) {
                            BaseCardAdapter.ITEM_TYPE_AUTO_PLAY_FOLLOW_CARD ->
                                ARouter.getInstance()
                                    .build(RouterPaths.DETAIL_VIDEO_DETAIL_ACTIVITY)
                                    .withInt(Constants.VIDEO_ID, data.content?.data?.id!!)
                                    .withString(
                                        Constants.RESOURCE_TYPE,
                                        data.content?.data?.resourceType
                                    )
                                    .navigation()
                            BaseCardAdapter.ITEM_TYPE_VIDEO_SMALL_CARD ->
                                ARouter.getInstance()
                                    .build(RouterPaths.DETAIL_VIDEO_DETAIL_ACTIVITY)
                                    .withInt(Constants.VIDEO_ID, data.id!!)
                                    .withString(Constants.RESOURCE_TYPE, data.resourceType)
                                    .navigation()
                        }
                    }
                })
            }
        }
    }

    override fun initData() {
        tabData?.let {
            presenter.getPersonHomeData(it.apiUrl)
        }
    }

    override fun onGetPersonHomeDataSuccess(data: BaseListData<ItemData>) {
        personHomeAdapter.setData(data.itemList)
    }

    override fun onGetPersonHomeDataError() {
    }
}