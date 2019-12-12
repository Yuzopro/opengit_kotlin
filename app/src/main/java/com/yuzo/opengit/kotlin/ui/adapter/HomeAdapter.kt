package com.yuzo.opengit.kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yuzo.lib.image.load
import com.yuzo.lib.ui.adapter.BasePagedAdapter
import com.yuzo.lib.ui.adapter.BaseViewHolder
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.http.service.bean.Entrylist
import com.yuzo.opengit.kotlin.http.service.bean.Tag

/**
 * Author: yuzo
 * Date: 2019-10-11
 */
class HomeAdapter : BasePagedAdapter<Entrylist, HomeViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_home, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.binds(getItem(position)!!, position)
    }

    companion object {
        private val diffCallback: DiffUtil.ItemCallback<Entrylist> =
            object : DiffUtil.ItemCallback<Entrylist>() {

                override fun areItemsTheSame(
                    oldItem: Entrylist,
                    newItem: Entrylist
                ): Boolean {
                    return oldItem.objectId == newItem.objectId
                }

                override fun areContentsTheSame(
                    oldItem: Entrylist,
                    newItem: Entrylist
                ): Boolean {
                    return oldItem.equals(newItem)
                }
            }
    }
}

class HomeViewHolder(view: View) : BaseViewHolder(view) {
    private val ivAvatar: ImageView = view.findViewById(R.id.iv_home_avatar)
    private val tvOwnerName: TextView = view.findViewById(R.id.tv_home_user_name)
    private val tvLanguage: TextView = view.findViewById(R.id.tv_home_language)
    private val tvName: TextView = view.findViewById(R.id.tv_home_name)
    private val tvDesc: TextView = view.findViewById(R.id.tv_home_desc)
    private val tvCollection: TextView = view.findViewById(R.id.tv_home_collection)
    private val tvComment: TextView = view.findViewById(R.id.tv_home_comment)
    private val tvView: TextView = view.findViewById(R.id.tv_home_view)

    fun binds(data: Entrylist, position: Int) {
        load(ivAvatar.context, ivAvatar, data.user.avatarLarge)
        tvOwnerName.text = data.user.username
        tvLanguage.text = getTags(data.tags)
        tvName.text = data.title
        tvDesc.text = data.content ?: "(No description, website, or topics provided.)"
        tvCollection.text = "${data.collectionCount}"
        tvComment.text = "${data.commentsCount}"
        tvView.text = "${data.viewsCount}"
    }
}

@BindingAdapter("home_tag")
fun bindHomeTag(view: TextView, tags: List<Tag>?) {
    view.text = getTags(tags)
}

private fun getTags(tags: List<Tag>?): String {
    var tag = ""
    if (tags != null && tags.isNotEmpty()) {
        var size = tags.size
        if (size > 2) {
            size = 2
        }
        for (i in 0 until size) {
            tag += tags[i].title + "/"
        }
    }
    return tag
}