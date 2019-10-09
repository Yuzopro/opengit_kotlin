package com.yuzo.opengit.kotlin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yuzo.opengit.kotlin.databinding.ListItemHomeBinding

/**
 * Author: yuzo
 * Date: 2019-10-08
 */
class ListAdapter constructor(private val context: Context) :
    PagedListAdapter<String, ListViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ListItemHomeBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val title = getItem(position)
        holder.apply {
            bind(title!!)
        }
    }

    companion object {
        private val diffCallback: DiffUtil.ItemCallback<String> =
            object : DiffUtil.ItemCallback<String>() {

                override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                    return oldItem == newItem
                }
            }
    }
}

class ListViewHolder(private val binding: ListItemHomeBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item : String) {
        binding.apply {
            title = item
            executePendingBindings()
        }
    }
}