package com.wsm.base_scaffold.building.ui.page

import android.util.SparseArray
import androidx.lifecycle.ViewModel

/**
 * @Author: WinSinMin
 * @Email: winsinmin@foxmail.com
 * @CreateDate: 2020/12/17 15:48
 * @Description:
 */
class DataBindingConfig constructor(layoutId: Int, vmVariableId: Int, stateViewModel: ViewModel) {
    val layoutId: Int = layoutId
    val vmVariableId = vmVariableId
    val stateViewModel = stateViewModel
    val bindingParams = SparseArray<Any>()

    open fun addBindingParam(variableId: Int, any: Any): DataBindingConfig {
        bindingParams.get(variableId) ?: let { bindingParams.put(variableId, any) }
        return this
    }


}