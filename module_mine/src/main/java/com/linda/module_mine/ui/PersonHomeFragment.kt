package com.linda.module_mine.ui

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
class PersonHomeFragment : BaseFragment(), PersonHomeContract.View {

    @JvmField
    @Autowired(name = Constants.TAB_DATA)
    var tabData: TabData? = null

    private var adapter: BaseCardAdapter? = null

    override fun getLayoutResId(): Int {
        return R.layout.mine_fragment_person_main_sub
    }

    override fun initView() {
        ARouter.getInstance().inject(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = BaseCardAdapter()
        recyclerView.adapter = adapter
    }

    override fun initData() {
        val presenter = PersonHomePresenter(this)
        tabData?.let {
            presenter.getPersonHomeData(it.apiUrl)
        }
    }

    override fun onGetPersonHomeDataSuccess(data: BaseListData<ItemData>) {
        adapter?.setData(data.itemList)
    }

    override fun onGetPersonHomeDataError() {
    }
}