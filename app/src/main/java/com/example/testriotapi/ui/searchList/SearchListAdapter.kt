package com.example.testriotapi.ui.searchList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testriotapi.R
import com.example.testriotapi.databinding.SearchListItemBinding
import com.example.testriotapi.db.User
import com.example.testriotapi.ui.viewModels.SummonerViewModel

/**
 * @author Daewon
 * @package com.example.testriotapi.ui.searchList
 * @email green201402317@gmail.com
 * @created 2022/01/01
 */

class SearchListAdapter(private val viewModel: SummonerViewModel, private val dataList: MutableList<User>) :
    RecyclerView.Adapter<SearchListAdapter.ViewHolder>()
    {

    class ViewHolder(private val binding: SearchListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: SummonerViewModel, item: User, position: Int) {
            binding.apply {
                this.viewModel = viewModel
                this.item = item
                executePendingBindings()
            }

            binding.deleteButton.setOnClickListener {
                viewModel.deleteUser(item)
            }
        }
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<SearchListItemBinding>(inflater, R.layout.search_list_item, parent, false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(viewModel, dataList[position], position)
        }

        override fun getItemCount(): Int {
            return dataList.size
        }
    }