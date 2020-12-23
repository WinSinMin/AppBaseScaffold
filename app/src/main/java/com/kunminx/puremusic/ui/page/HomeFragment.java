package com.kunminx.puremusic.ui.page;

import com.kunminx.puremusic.BR;
import com.kunminx.puremusic.R;
import com.kunminx.puremusic.ui.state.DrawerViewModel;
import com.wsm.base_scaffold.building.ui.page.BaseFragment;
import com.wsm.base_scaffold.building.ui.page.DataBindingConfig;

import org.jetbrains.annotations.NotNull;

public class HomeFragment extends BaseFragment {
    private DrawerViewModel stateViewModel;

    @Override
    protected void initViewModel() {
        stateViewModel = getFragmentScopeViewModel(DrawerViewModel.class);

    }

    @NotNull
    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_home, BR.vm, stateViewModel);
    }

    class ClickEvent {
        public void detail() {
            nav().navigate(R.id.action_home_to_detail);
        }
    }


}
