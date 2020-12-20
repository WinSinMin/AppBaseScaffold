package com.wsm.base_scaffold.building.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

/**
 * @Author: WinSinMin
 * @Email: winsinmin@foxmail.com
 * @CreateDate: 2020/12/17 19:09
 * @Description:
 */
class CommonViewPagerAdapter(count: Int, enableDestroyItem: Boolean, title: Array<String>) : PagerAdapter() {
    private val count: Int
    private val enableDestroyItem: Boolean
    private val title: Array<String>
    override fun getCount(): Int {
        return count
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return container.getChildAt(position)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if (enableDestroyItem) {
            container.removeView(`object` as View)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }

    init {
        this.count = count
        this.enableDestroyItem = enableDestroyItem
        this.title = title
    }
}
