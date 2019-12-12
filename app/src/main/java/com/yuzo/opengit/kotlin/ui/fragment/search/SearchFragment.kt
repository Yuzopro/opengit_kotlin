package com.yuzo.opengit.kotlin.ui.fragment.search

import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.yuzo.lib.tool.ToastUtil
import com.yuzo.lib.ui.fragment.BaseFragment
import com.yuzo.opengit.kotlin.R
import com.yuzo.opengit.kotlin.databinding.FragmentSearchBinding
import com.yuzo.opengit.kotlin.ui.DrawerCoordinateHelper
import com.yuzo.opengit.kotlin.ui.viewmodel.search.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private var tabs: List<String>? = null

    override val layoutId: Int = R.layout.fragment_search

    private var searchViewModel: SearchViewModel? = null

    private var repoFragment: SearchRepoFragment? = null
    private var userFragment: SearchUserFragment? = null
    private var issueFragment: SearchIssueFragment? = null

    override fun initData(binding: FragmentSearchBinding) {
        super.initData(binding)

        searchViewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SearchViewModel() as T
            }
        })[SearchViewModel::class.java]

        lifecycle.addObserver(DrawerCoordinateHelper.getInstance())

        tabs = listOf(
            getString(R.string.search_item_0),
            getString(R.string.search_item_1),
            getString(R.string.search_item_2)
        )

        binding.model = searchViewModel

        repoFragment = SearchRepoFragment()
        userFragment = SearchUserFragment()
        issueFragment = SearchIssueFragment()
    }

    override fun initView() {
        super.initView()

        view_pager_search.adapter = object : FragmentPagerAdapter(childFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return getCurrentFragment(position)!!
            }

            override fun getCount(): Int {
                return tabs!!.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return tabs?.get(position)
            }
        }
        view_pager_search?.offscreenPageLimit = tabs!!.size
        view_pager_search?.currentItem = 0

        tab_layout_search?.setupWithViewPager(view_pager_search)

        searchViewModel?.actionBtnState?.observe(this, Observer {
            if (it) {
                iv_search_clear_text?.visibility = View.VISIBLE
                iv_search_do?.visibility = View.VISIBLE
            } else {
                iv_search_clear_text?.visibility = View.INVISIBLE
                iv_search_do?.visibility = View.VISIBLE
            }
        })

        iv_search_back?.setOnClickListener {
            nav().popBackStack()
        }

        iv_search_do?.setOnClickListener {
            doSearch()
        }

        iv_search_clear_text?.setOnClickListener {
            et_search_content?.setText("")
        }

        et_search_content?.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearch()
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun getCurrentFragment(position : Int) : Fragment? {
        if (position == 0) {
            return repoFragment
        } else if (position == 1) {
            return userFragment
        } else {
            return issueFragment
        }
    }

    private fun doSearch() {
        val text = et_search_content?.text.toString().trim()
        if (text.isEmpty()) {
            ToastUtil.showShort(R.string.search_text_is_not_null)
            return
        }
        val currentItem = view_pager_search?.currentItem
        val fragment = getCurrentFragment(currentItem?:0)
        if (fragment is SearchItemFragment<*, *, *, *>) {
            fragment.doSearch(text)
        }
    }

}