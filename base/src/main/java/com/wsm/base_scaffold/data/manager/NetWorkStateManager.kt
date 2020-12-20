package com.wsm.base_scaffold.data.manager

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.Utils

/**
 * @Author: WinSinMin
 * @Email: winsinmin@foxmail.com
 * @CreateDate: 2020/12/17 14:50
 * @Description:
 */
class NetWorkStateManager private constructor() : DefaultLifecycleObserver {
    companion object {
        val instance = Holder.create
    }

    private object Holder {
        val create = NetWorkStateManager()
    }

    private lateinit var netWorkStateReceive: NetWorkStateReceive

    override fun onResume(owner: LifecycleOwner) {
        netWorkStateReceive = NetWorkStateReceive()
        var filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        Utils.getApp().applicationContext.registerReceiver(netWorkStateReceive, filter)
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Utils.getApp().applicationContext.unregisterReceiver(netWorkStateReceive)
    }


}