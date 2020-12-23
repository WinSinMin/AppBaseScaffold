package com.kunminx.puremusic.ui.page

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.plusAssign
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.kunminx.puremusic.BR
import com.kunminx.puremusic.R
import com.kunminx.puremusic.databinding.FragmentKeepNavBinding
import com.kunminx.puremusic.ui.state.LoginViewModel
import com.wsm.base_scaffold.building.ui.page.BaseFragment
import com.wsm.base_scaffold.building.ui.page.DataBindingConfig
import com.wsm.base_scaffold.building.ui.page.KeepStateNavigator

class KeepNavigationFragment : BaseFragment() {
    lateinit var stateViewModel: LoginViewModel

    override fun initViewModel() {
        stateViewModel = getFragmentScopeViewModel(LoginViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_keep_nav, BR.vm, stateViewModel)
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment)!!
        val navigator = KeepStateNavigator(parentActivity!!, navHostFragment.childFragmentManager, R.id.nav_host_fragment)
        navController.navigatorProvider += navigator
        navController.setGraph(R.navigation.nav_graph)
        stateViewModel.navController.value = navController
    }
}