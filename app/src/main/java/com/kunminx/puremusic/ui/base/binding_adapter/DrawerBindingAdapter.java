package com.kunminx.puremusic.ui.base.binding_adapter;

import androidx.core.view.GravityCompat;
import androidx.databinding.BindingAdapter;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.puremusic.ui.page.adapter.PlaylistAdapter;

/**
 * Create by KunMinX at 2020/3/13
 */
public class DrawerBindingAdapter {

    @BindingAdapter(value = {"isOpenDrawer"}, requireAll = false)
    public static void openDrawer(DrawerLayout drawerLayout, boolean isOpenDrawer) {
        if (isOpenDrawer && !drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.openDrawer(GravityCompat.START);
        } else {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @BindingAdapter(value = {"allowDrawerOpen"}, requireAll = false)
    public static void allowDrawerOpen(DrawerLayout drawerLayout, boolean allowDrawerOpen) {
        drawerLayout.setDrawerLockMode(allowDrawerOpen
                ? DrawerLayout.LOCK_MODE_UNLOCKED
                : DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @BindingAdapter(value = {"bindDrawerListener"}, requireAll = false)
    public static void listenDrawerState(DrawerLayout drawerLayout, DrawerLayout.SimpleDrawerListener listener) {
        drawerLayout.addDrawerListener(listener);
    }

    @BindingAdapter("setPlayListAdapter")
    public static void setPlayListAdapter(RecyclerView recyclerView, PlaylistAdapter playlistAdapter) {
        if (recyclerView.getAdapter() == null) {
            if (playlistAdapter != null) {
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                recyclerView.setAdapter(playlistAdapter);
            }
        }
    }

}
