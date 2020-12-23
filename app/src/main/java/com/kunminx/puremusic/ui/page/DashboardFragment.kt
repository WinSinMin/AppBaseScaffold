package com.kunminx.puremusic.ui.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.*
import com.kunminx.puremusic.BR
import com.kunminx.puremusic.R
import com.kunminx.puremusic.databinding.FragmentDashboardBinding
import com.kunminx.puremusic.ui.state.DrawerViewModel
import com.wsm.base_scaffold.building.ui.page.BaseFragment
import com.wsm.base_scaffold.building.ui.page.DataBindingConfig

class DashboardFragment : BaseFragment() {
    lateinit var drawerViewModel: DrawerViewModel
    override fun initViewModel() {
        drawerViewModel = getFragmentScopeViewModel(DrawerViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_dashboard, BR.vm, drawerViewModel)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val binding = getBinding() as FragmentDashboardBinding
        val adapter = DashboardAdapter()
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        binding.recycler.adapter = adapter

        val data = (1..50).map { "Item $it" }
        adapter.submitList(data)
    }
}

class DashboardAdapter: ListAdapter<String, DashboardAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(android.R.id.text1).text = getItem(position)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}