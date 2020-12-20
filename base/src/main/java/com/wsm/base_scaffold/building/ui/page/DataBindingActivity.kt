package com.wsm.base_scaffold.building.ui.page

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.forEach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @Author: WinSinMin
 * @Email: winsinmin@foxmail.com
 * @CreateDate: 2020/12/17 15:44
 * @Description:
 */
abstract class DataBindingActivity : AppCompatActivity() {
    private var mBinding: ViewDataBinding? = null

    protected abstract fun initViewModel()

    protected abstract fun getDataBindingConfig(): DataBindingConfig

    //谨慎使用
    protected fun getBinding(): ViewDataBinding? {
        return mBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        var dataBindingConfig = getDataBindingConfig()
        var binding = DataBindingUtil.setContentView<ViewDataBinding>(this, dataBindingConfig.layoutId)
        binding.lifecycleOwner = this
        binding.setVariable(dataBindingConfig.vmVariableId, dataBindingConfig.stateViewModel)
        var bindingParams = dataBindingConfig.bindingParams
        bindingParams.forEach { key, value -> binding.setVariable(key, value) }
        mBinding = binding
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding?.unbind()
        mBinding = null
    }


}