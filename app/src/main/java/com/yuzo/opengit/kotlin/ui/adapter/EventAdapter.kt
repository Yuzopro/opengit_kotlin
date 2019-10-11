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
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yuzo.lib.ui.adapter.BasePagedListAdapter
import com.yuzo.lib.ui.adapter.BaseViewHolder
import com.yuzo.opengit.kotlin.databinding.ListItemEventBinding
import com.yuzo.opengit.kotlin.http.service.bean.Event
import com.yuzo.opengit.kotlin.http.service.bean.Type

/**
 * Author: yuzo
 * Date: 2019-10-11
 */
class EventAdapter :
    BasePagedListAdapter<Event, ListItemEventBinding, EventViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            ListItemEventBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
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

class EventViewHolder(private val binding: ListItemEventBinding) :
    BaseViewHolder<Event, ListItemEventBinding>(binding) {

    override fun bind(item: Event) {
        binding.apply {
            this.item = item
            executePendingBindings()
        }
    }

    companion object {
        private const val TAG = "RepoViewHolder"
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