package com.wsm.base_scaffold.building.ui.page

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.forEach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.LogUtils

/**
 * @Author: WinSinMin
 * @Email: winsinmin@foxmail.com
 * @CreateDate: 2020/12/17 16:27
 * @Description:
 */
abstract class DataBindingFragment : Fragment() {
    protected lateinit var parentActivity: AppCompatActivity
    private var binding: ViewDataBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentActivity = context as AppCompatActivity
    }

    protected abstract fun initViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    protected abstract fun getDataBindingConfig(): DataBindingConfig

    //谨慎使用,破坏试图调用的一致性，埋下隐患
    protected fun getBinding(): ViewDataBinding? {
        return binding
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var databindingConfig = getDataBindingConfig()
        var binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, databindingConfig.layoutId, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(databindingConfig.vmVariableId, databindingConfig.stateViewModel)
        var bindingParams = databindingConfig.bindingParams
        LogUtils.v(bindingParams)
        bindingParams.forEach { key, value -> binding.setVariable(key, value) }
        this.binding = binding
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.unbind()
        binding = null
    }


}