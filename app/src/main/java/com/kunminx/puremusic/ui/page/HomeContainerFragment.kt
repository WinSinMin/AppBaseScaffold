package com.kunminx.puremusic.ui.page

import com.kunminx.puremusic.BR
import com.kunminx.puremusic.R
import com.kunminx.puremusic.ui.state.DrawerViewModel
import com.wsm.base_scaffold.building.ui.page.BaseFragment
import com.wsm.base_scaffold.building.ui.page.DataBindingConfig

class HomeContainerFragment :BaseFragment(){
    lateinit var drawerViewModel: DrawerViewModel
    override fun initViewModel() {
        drawerViewModel = getFragmentScopeViewModel(DrawerViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_home_container,BR.vm,drawerViewModel)
    }

}