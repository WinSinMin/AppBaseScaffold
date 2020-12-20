package com.wsm.base_scaffold.building.ui.page

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.BarUtils
import com.wsm.base_scaffold.building.BaseApplication
import com.wsm.base_scaffold.data.manager.NetWorkStateManager

/**
 * @Author: WinSinMin
 * @Email: winsinmin@foxmail.com
 * @CreateDate: 2020/12/17 16:06
 * @Description:
 */
abstract class BaseActivity : DataBindingActivity() {
    private var activityProvider: ViewModelProvider? = null
    private var applicationProvider: ViewModelProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT)
        BarUtils.setStatusBarLightMode(this, true)
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(NetWorkStateManager.instance)
    }

    protected open fun <T : ViewModel> getActivityScopeViewModel(modelClass: Class<T>): T {
        activityProvider ?: let { activityProvider = ViewModelProvider(this) }
        return activityProvider!!.get(modelClass)
    }

    protected open fun <T : ViewModel> getApplicationScopeViewModel(modelClass: Class<T>): T {
        applicationProvider
                ?: let { applicationProvider = ViewModelProvider(applicationContext as BaseApplication, getAppFactoty(this)) }
        return applicationProvider!!.get(modelClass)
    }

    private fun checkApplication(activity: Activity): Application {
        var application = activity.application
        application
                ?: let { throw IllegalStateException("Your activity/fragment is not yet attached to Application. You can't request ViewModel before onCreate call.") }
        return application
    }

    private fun getAppFactoty(activity: Activity): ViewModelProvider.Factory {
        var application = checkApplication(activity)
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }

    protected open fun toggleSoftInput() = (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)

    protected open fun openUrlInBrowser(url: String?) = startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}