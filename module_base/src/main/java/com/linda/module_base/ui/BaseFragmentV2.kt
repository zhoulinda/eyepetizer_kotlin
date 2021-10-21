package com.linda.module_base.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * 描述 :     基类Fragment
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/7/23
 */
abstract class BaseFragmentV2<T : ViewDataBinding>(@LayoutRes val layoutId: Int) : Fragment() {

    protected var binding: T? = null

    protected var mContext: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            mContext = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = DataBindingUtil.inflate<T>(inflater, layoutId, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    abstract fun initView()

    abstract fun initData()

    override fun onDestroy() {
        super.onDestroy()
    }
}
