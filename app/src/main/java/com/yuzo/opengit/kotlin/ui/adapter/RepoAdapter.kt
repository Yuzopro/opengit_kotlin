package com.yuzo.opengit.kotlin.ui.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import com.yuzo.lib.image.load
import com.yuzo.lib.ui.adapter.BasePagedAdapter
import com.yuzo.lib.ui.adapter.BaseViewHolder
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.http.service.bean.Repo
import de.hdodenhof.circleimageview.CircleImageView

class RepoAdapter : BasePagedAdapter<Repo, RepoViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_repo, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.binds(getItem(position)!!, position)
    }

    companion object {
        private val diffCallback: DiffUtil.ItemCallback<Repo> =
            object : DiffUtil.ItemCallback<Repo>() {

                override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                    return oldItem == newItem
                }
            }
    }
}

class RepoViewHolder(private val view: View) : BaseViewHolder(view) {
    private val ivAvatar: ImageView = view.findViewById(R.id.iv_repo_avatar)

    private val tvOwnerName: TextView = view.findViewById(R.id.tv_repo_user_name)
    private val ivLanguageColor: CircleImageView = view.findViewById(R.id.iv_repo_language_color)

    private val tvLanguage: TextView = view.findViewById(R.id.tv_repo_language)
    private val tvRepoName: TextView = view.findViewById(R.id.tv_repo_name)
    private val tvRepoDesc: TextView = view.findViewById(R.id.tv_repo_desc)
    private val tvStar: TextView = view.findViewById(R.id.tv_repo_star)
    private val tvIssue: TextView = view.findViewById(R.id.tv_repo_issue)
    private val tvFork: TextView = view.findViewById(R.id.tv_repo_fork)

    fun binds(data: Repo, position: Int) {
        load(ivAvatar.context, ivAvatar, data?.archive_url)

        tvOwnerName.text = data.owner.login

        ivLanguageColor.setImageDrawable(getLanguageColor(data.language))
        ivLanguageColor.visibility = if (data.language == null) View.GONE else View.VISIBLE
        tvLanguage.text = data.language

        tvRepoName.text = data.full_name
        tvRepoDesc.text = data.description ?: "(No description, website, or topics provided.)"

        tvStar.text = "${data.stargazers_count}"
        tvIssue.text = "${data.open_issues_count}"
        tvFork.text = "${data.forks_count}"
    }

    private fun getLanguageColor(language: String?): Drawable {
        if (language == null) return ColorDrawable(Color.TRANSPARENT)

        val colorProvider: (Int) -> Drawable = { resId ->
            ColorDrawable(ContextCompat.getColor(view.context, resId))
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
}