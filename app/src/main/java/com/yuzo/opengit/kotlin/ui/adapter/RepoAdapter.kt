package com.yuzo.opengit.kotlin.ui.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yuzo.lib.ui.adapter.BasePagedAdapter
import com.yuzo.lib.ui.adapter.BaseViewHolder
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.databinding.ListItemRepoBinding
import com.yuzo.opengit.kotlin.http.service.bean.Repo

/**
 * Author: yuzo
 * Date: 2019-10-08
 */
class RepoAdapter : BasePagedAdapter<Repo>(diffCallback) {
    private var mBinding: ListItemRepoBinding? = null

    override fun getView(parent: ViewGroup, viewType: Int): View {
        mBinding = ListItemRepoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return mBinding!!.root
    }

    override fun setItem(holder: BaseViewHolder, item: Repo?, position: Int) {
        mBinding?.apply {
            this.item = item
            executePendingBindings()
        }
    }

    companion object {
        private val diffCallback: DiffUtil.ItemCallback<Repo> =
            object : DiffUtil.ItemCallback<Repo>() {
                override fun areItemsTheSame(
                    oldItem: Repo,
                    newItem: Repo
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Repo,
                    newItem: Repo
                ): Boolean {
                    return oldItem.equals(newItem)
                }
            }
    }
}

@BindingAdapter("language_color")
fun bindLanguageColor(view: ImageView, language: String?) {
    view.setImageDrawable(getLanguageColor(view.context, language))
}

private fun getLanguageColor(context: Context, language: String?): Drawable {
    if (language == null) return ColorDrawable(Color.TRANSPARENT)

    val colorProvider: (Int) -> Drawable = { resId ->
        ColorDrawable(ContextCompat.getColor(context, resId))
    }

    return colorProvider(
        when (language) {
            "Kotlin" -> R.color.color_language_kotlin
            "Java" -> R.color.color_language_java
            "JavaScript" -> R.color.color_language_js
            "Python" -> R.color.color_language_python
            "HTML" -> R.color.color_language_html
            "CSS" -> R.color.color_language_css
            "Shell" -> R.color.color_language_shell
            "C++" -> R.color.color_language_cplus
            "C" -> R.color.color_language_c
            "ruby" -> R.color.color_language_ruby
            else -> R.color.color_language_other
        }
    )
}