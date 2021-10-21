package com.linda.module_mine.ui

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.linda.module_base.adapter.BaseCardAdapter
import com.linda.module_base.bean.ItemData
import com.linda.module_base.bean.mine.TabData
import com.linda.module_base.constants.Constants
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.listener.OnMultiViewClickListener
import com.linda.module_base.ui.BaseFragmentV2
import com.linda.module_mine.R
import com.linda.module_mine.databinding.MineFragmentPersonMainSubBinding
import com.linda.module_mine.model.PersonHomeViewModel
import com.linda.module_mine.repository.PersonHomeRepository
import kotlinx.android.synthetic.main.mine_fragment_person_main_sub.*

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/5
 */
@Route(path = RouterPaths.PERSON_HOME_FRAGMENT)
class PersonHomeFragment :
    BaseFragmentV2<MineFragmentPersonMainSubBinding>(R.layout.mine_fragment_person_main_sub) {

    @JvmField
    @Autowired(name = Constants.TAB_DATA)
    var tabData: TabData? = null

    private val linearLayoutManager by lazy { LinearLayoutManager(context) }
    private val personHomeAdapter by lazy { BaseCardAdapter() }
    private val viewModel by lazy { PersonHomeViewModel(PersonHomeRepository()) }

    override fun initView() {
        ARouter.getInstance().inject(this)
        viewModel.run {
            data.observe(this@PersonHomeFragment, Observer {
                personHomeAdapter.setData(it)
            })
        }
        recyclerView.run {
            layoutManager = linearLayoutManager
            adapter = personHomeAdapter.apply {
                setOnMultiViewClickListener(object :
                    OnMultiViewClickListener<ItemData> {
                    override fun onViewClick(position: Int, view: View, data: ItemData, type: Int) {
                        if (type == BaseCardAdapter.ITEM_TYPE_AUTO_PLAY_FOLLOW_CARD) {
                            ARouter.getInstance().build(RouterPaths.DETAIL_VIDEO_DETAIL_ACTIVITY)
                                .withInt(Constants.VIDEO_ID, data.content?.data?.id!!)
                                .withString(
                                    Constants.RESOURCE_TYPE,
                                    data.content?.data?.resourceType
                                )
                                .navigation()
                        } else if (type == BaseCardAdapter.ITEM_TYPE_VIDEO_SMALL_CARD) {
                            ARouter.getInstance().build(RouterPaths.DETAIL_VIDEO_DETAIL_ACTIVITY)
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
        tabData?.apiUrl?.let { viewModel.getPersonHomeData(it) }
    }
}