package com.wsm.base_scaffold.building.ui.page

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.wsm.base_scaffold.building.BaseApplication

/**
 * @Author: WinSinMin
 * @Email: winsinmin@foxmail.com
 * @CreateDate: 2020/12/17 16:25
 * @Description:
 */
abstract class BaseFragment : DataBindingFragment() {

    private var fragmentProvider: ViewModelProvider? = null
    private var activityProvider: ViewModelProvider? = null
    private var applicationProvider: ViewModelProvider? = null
    private val startOffset = 200L

    private val handler by lazy { Handler(Looper.getMainLooper()) }
    protected val navController by lazy { nav() }

    protected  var animationLoaded: Boolean = false

    protected open fun <T : ViewModel> getFragmentScopeViewModel(modelClass: Class<T>): T {
        fragmentProvider ?: let { fragmentProvider = ViewModelProvider(this) }
        return fragmentProvider!!.get(modelClass)
    }

    protected open fun <T : ViewModel> getActivityScopeViewModel(modelClass: Class<T>): T {
        activityProvider ?: let { activityProvider = ViewModelProvider(parentActivity) }
        return activityProvider!!.get(modelClass)
    }

    protected open fun <T : ViewModel> getApplicationScopeViewModel(modelClass: Class<T>): T {
        applicationProvider
                ?: let { applicationProvider = ViewModelProvider(parentActivity.application as BaseApplication, getApplicationFactory(parentActivity)) }
        return applicationProvider!!.get(modelClass)
    }

    private fun checkApplication(activity: Activity): Application {
        var application = activity.application
        application
                ?: let { throw IllegalStateException("Your activity/fragment is not yet attached to Application. You can't request ViewModel before onCreate call.") }
        return application
    }

    private fun checkActivity(fragment: Fragment) {
        var activity = fragment.activity
        activity
                ?: let { throw IllegalStateException("Can't create ViewModelProvider for detached fragment") }
    }

    private fun getApplicationFactory(activity: Activity): ViewModelProvider.Factory {
        checkActivity(this)
        var application = checkApplication(activity)
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }


    protected open fun nav(): NavController = NavHostFragment.findNavController(this)

    protected open fun loadInitData() {}

    protected open fun toggleSoftInput() = (parentActivity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)

    protected open fun openUrlInBrowser(url: String?) = startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        if (enter) {
            if (nextAnim > 0) {
                val animation = AnimationUtils.loadAnimation(requireActivity(), nextAnim)
                animation.startOffset = startOffset
                return animation
            }
        } else {
            if (nextAnim > 0) {
                val animation = AnimationUtils.loadAnimation(requireActivity(), nextAnim)
                animation.startOffset = startOffset
                return animation
            }
        }
        return super.onCreateAnimation(transit, enter, nextAnim)
    }


}