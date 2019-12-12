package com.yuzo.opengit.kotlin.ui.adapter

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yuzo.lib.image.load
import com.yuzo.lib.tool.getMultiTime
import com.yuzo.lib.tool.transTimeStamp
import com.yuzo.lib.ui.adapter.BasePagedAdapter
import com.yuzo.lib.ui.adapter.BaseViewHolder
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.http.service.bean.Event
import com.yuzo.opengit.kotlin.http.service.bean.Type

/**
 * Author: yuzo
 * Date: 2019-10-11
 */
class EventAdapter : BasePagedAdapter<Event, EventViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.binds(getItem(position)!!, position)
    }

    companion object {
        private val diffCallback: DiffUtil.ItemCallback<Event> =
            object : DiffUtil.ItemCallback<Event>() {
                override fun areItemsTheSame(
                    oldItem: Event,
                    newItem: Event
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Event,
                    newItem: Event
                ): Boolean {
                    return oldItem.equals(newItem)
                }
            }
    }
}

class EventViewHolder(view: View) : BaseViewHolder(view) {
    private val ivAvatar: ImageView = view.findViewById(R.id.iv_event_avatar)
    private val tvOwnerName: TextView = view.findViewById(R.id.tv_event_user_name)
    private val tvDate: TextView = view.findViewById(R.id.tv_event_date)
    private val tvContent: TextView = view.findViewById(R.id.tv_event_content)

    fun binds(data: Event, position: Int) {
        load(ivAvatar.context, ivAvatar, data.actor.avatarUrl)
        tvOwnerName.text = data.actor.displayLogin
        tvDate.text = getMultiTime(transTimeStamp(data.created_at))
        tvContent.text = getTitle(tvContent, data)
    }
}
@BindingAdapter("event_title")
fun bindEventTitle(view: TextView, data: Event) {
    view.text = getTitle(view, data)
}

private fun getTitle(view: TextView, data: Event): CharSequence {
    val actor = data.actor.displayLogin
    val action = when (data.type) {
        Type.WatchEvent -> "starred"
        Type.CreateEvent -> "created"
        Type.ForkEvent -> "forked"
        Type.PushEvent -> "pushed"
        else -> data.type.name
    }
    val repo = data.repo.name

    val actorSpan = object : ClickableSpan() {
        override fun onClick(widget: View?) {
//            observer.onNext(data.actor.url)
        }
    }
    val repoSpan = object : ClickableSpan() {
        override fun onClick(widget: View?) {
//            observer.onNext(data.repo.url)
        }
    }
    val styleSpan = StyleSpan(Typeface.BOLD)
    val styleSpan2 = StyleSpan(Typeface.BOLD)

    view.movementMethod = LinkMovementMethod.getInstance()

    return SpannableStringBuilder().apply {
        append("$actor $action $repo")
        setSpan(actorSpan, 0, actor.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        setSpan(styleSpan, 0, actor.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        setSpan(
            repoSpan,
            actor.length + action.length + 2,
            actor.length + action.length + repo.length + 2,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        setSpan(
            styleSpan2,
            actor.length + action.length + 2,
            actor.length + action.length + repo.length + 2,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}