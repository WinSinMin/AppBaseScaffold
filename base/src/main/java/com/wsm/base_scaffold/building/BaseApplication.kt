package com.wsm.base_scaffold.building

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.multidex.MultiDexApplication
import com.blankj.utilcode.util.Utils

/**
 * @Author: WinSinMin
 * @Email: winsinmin@foxmail.com
 * @CreateDate: 2020/12/17 14:39
 * @Description:
 */
abstract class BaseApplication : MultiDexApplication(), ViewModelStoreOwner {
    private lateinit var appViewModelStore: ViewModelStore
    override fun onCreate() {
        super.onCreate()
        appViewModelStore = ViewModelStore()
        Utils.init(this)
    }


    override fun getViewModelStore(): ViewModelStore {
        return appViewModelStore
    }
}