package com.wsm.base_scaffold.building.ui.binding_adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter

/**
 * @Author: WinSinMin
 * @Email: winsinmin@foxmail.com
 * @CreateDate: 2020/12/17 19:58
 * @Description:
 */
object RecyclerViewBindingAdapter {
//    @BindingAdapter(value = ["setAdapter", "setNewData", "AddData"], requireAll = false)
//    @JvmStatic
//    fun bindList(recyclerView: RecyclerView, baseQuickAdapter: BaseQuickAdapter<*, DataBindBaseViewHolder>?, newData: List<Nothing>?, addData: List<Nothing>?) {
//        LogUtils.v("setList",baseQuickAdapter,newData)
//        recyclerView.adapter ?: let {
//            if (recyclerView.layoutManager != null) {
//                if (recyclerView.layoutManager is GridLayoutManager) {
//                    val spanCount: Int = (recyclerView.layoutManager as GridLayoutManager).getSpanCount()
//                    recyclerView.layoutManager = GridLayoutManager(recyclerView.context, spanCount)
//                } else if (recyclerView.layoutManager is LinearLayoutManager) {
//                    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
//                } else if (recyclerView.layoutManager is StaggeredGridLayoutManager) {
//                    val spanCount: Int = (recyclerView.layoutManager as StaggeredGridLayoutManager).getSpanCount()
//                    val orientation: Int = (recyclerView.layoutManager as StaggeredGridLayoutManager).getOrientation()
//                    recyclerView.layoutManager = GridLayoutManager(recyclerView.context, orientation)
//                }
//                recyclerView.adapter = baseQuickAdapter
////                baseQuickAdapter.addData(addData)
//            }
//        }
//        newData?.let { list ->
//            recyclerView.adapter?.let {
//                (it as BaseQuickAdapter<*, *>).setList(list)
//                LogUtils.v("setList", GsonUtils.toJson(it))
//            }
//        }
//    }

    @BindingAdapter("setAdapter")
    @JvmStatic
    fun setAdapter(recyclerView: RecyclerView, baseQuickAdapter: BaseQuickAdapter<*, *>?) {
        LogUtils.v("setAdapter", baseQuickAdapter)
        recyclerView.adapter ?: let {
            if (recyclerView.layoutManager != null) {
                if (recyclerView.layoutManager is GridLayoutManager) {
                    val spanCount: Int = (recyclerView.layoutManager as GridLayoutManager).getSpanCount()
                    recyclerView.layoutManager = GridLayoutManager(recyclerView.context, spanCount)
                } else if (recyclerView.layoutManager is LinearLayoutManager) {
                    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
                } else if (recyclerView.layoutManager is StaggeredGridLayoutManager) {
                    val spanCount: Int = (recyclerView.layoutManager as StaggeredGridLayoutManager).getSpanCount()
                    val orientation: Int = (recyclerView.layoutManager as StaggeredGridLayoutManager).getOrientation()
                    recyclerView.layoutManager = GridLayoutManager(recyclerView.context, orientation)
                }
                recyclerView.adapter = baseQuickAdapter
//                baseQuickAdapter.addData(addData)
            }
        }
    }

    @BindingAdapter("setNewData")
    @JvmStatic
    fun setNewData(recyclerView: RecyclerView, newData: List<Nothing>?) {
        LogUtils.v("setNewData", newData)
        newData?.let { list ->
            recyclerView.adapter?.let {
                (it as BaseQuickAdapter<*, *>).setList(list)
            }
        }
    }


}