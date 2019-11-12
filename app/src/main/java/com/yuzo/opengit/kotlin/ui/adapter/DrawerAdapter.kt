package com.yuzo.opengit.kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.yuzo.lib.ui.adapter.BaseViewHolder
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.databinding.ListItemDrawerBinding
import com.yuzo.opengit.kotlin.ui.bean.DrawerBean

/**
 * Author: yuzo
 * Date: 2019-11-01
 */
class DrawerAdapter : RecyclerView.Adapter<BaseViewHolder>() {
    var listener : OnItemClickListener?=null

    interface OnItemClickListener {
        fun OnItemClick(position: Int)
    }

    private var mBinding: ListItemDrawerBinding? = null

    private val mList: MutableList<DrawerBean> = ArrayList()

    fun addDatas(list: MutableList<DrawerBean>) {
        mList.clear()
        if (list.isNotEmpty()) {
            mList.addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        mBinding = ListItemDrawerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return BaseViewHolder(mBinding!!.root)
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        mBinding?.apply {
            this.item = mList.get(position)
            executePendingBindings()

            ivDrawerItem.setImageResource(item!!.icon)
            root.setOnClickListener {
                listener?.OnItemClick(position)
            }
        }
    }
}