package com.linda.module_mine.ui

import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.linda.module_base.bean.Member
import com.linda.module_base.constants.Constants
import com.linda.module_base.constants.RouterPaths
import com.linda.lib_common.utils.AppUtil
import com.linda.lib_common.utils.PrefsUtil
import com.linda.module_base.ui.BaseFragment
import com.linda.module_mine.MinePresenter
import com.linda.module_mine.R
import com.linda.module_mine.databinding.MineFragmentMineBinding
import kotlinx.android.synthetic.main.mine_fragment_mine.*

/**
 * 描述 :     我的Fragment
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/7/25
 */
@Route(path = RouterPaths.MINE_FRAGMENT)
class MineFragment : BaseFragment<MineFragmentMineBinding>(R.layout.mine_fragment_mine),
    View.OnClickListener {

    private var userData: Member by PrefsUtil(
        Constants.USER_DATA, Member(
            0, "", "", "", "", ""
        )
    )

    private var minePresenter: MinePresenter? = null

    override fun initView() {
        toPersonMainPage.setOnClickListener(this)
        portrait.setOnClickListener(this)
        loginPromote.setOnClickListener(this)
        store.setOnClickListener(this)
        cache.setOnClickListener(this)
        myAttention.setOnClickListener(this)
        lookHistory.setOnClickListener(this)
        notificationSwitch.setOnClickListener(this)
        mMyBadge.setOnClickListener(this)
        feedback.setOnClickListener(this)
        version.text = activity?.let { "version " + AppUtil.getVersionName(it) }
    }

    override fun initData() {
        minePresenter = MinePresenter()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.portrait ->
                if (userData.uid == 0) {
                    ARouter.getInstance().build(RouterPaths.LOGIN_ACTIVITY).navigation()
                } else {
                    ARouter.getInstance().build(RouterPaths.PERSON_MAIN_ACTIVITY)
                        .withInt(Constants.USER_ID, userData.uid).navigation()
                }
            R.id.toPersonMainPage ->
                ARouter.getInstance().build(RouterPaths.PERSON_MAIN_ACTIVITY)
                    .withInt(Constants.USER_ID, userData.uid).navigation()
            R.id.store ->
                Toast.makeText(mContext, "收藏", Toast.LENGTH_SHORT).show()
            R.id.cache ->
                Toast.makeText(mContext, "缓存", Toast.LENGTH_SHORT).show()
            R.id.loginPromote ->
                Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show()
            R.id.myAttention ->
                Toast.makeText(mContext, "我的关注", Toast.LENGTH_SHORT).show()
            R.id.lookHistory ->
                Toast.makeText(mContext, "历史记录", Toast.LENGTH_SHORT).show()
            R.id.notificationSwitch ->
                Toast.makeText(mContext, "通知开关", Toast.LENGTH_SHORT).show()
            R.id.mMyBadge ->
                Toast.makeText(mContext, "我的徽章", Toast.LENGTH_SHORT).show()
            R.id.consult ->
                Toast.makeText(mContext, "客服咨询", Toast.LENGTH_SHORT).show()
            R.id.contribute ->
                Toast.makeText(mContext, "我要投稿", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        if (userData.uid == 0) {
            nickName.visibility = View.INVISIBLE
            loginPromote.visibility = View.VISIBLE
            toPersonMainPage.visibility = View.GONE
        } else {
            Glide.with(this)
                .load(userData.avatar)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(portrait)
            nickName.text = userData.nick
            nickName.visibility = View.VISIBLE
            loginPromote.visibility = View.GONE
            toPersonMainPage.visibility = View.VISIBLE
        }
    }
}