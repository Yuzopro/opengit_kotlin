package com.yuzo.lib.ui.view.webview

import android.content.Intent
import android.view.ViewGroup
import android.webkit.WebView

import androidx.databinding.ViewDataBinding
import com.just.agentweb.*

import com.yuzo.lib.ui.fragment.BaseFragment

/**
 * Author: yuzo
 * Date: 2019-11-21
 */
abstract class BaseAgentWebFragment<T : ViewDataBinding> : BaseFragment<T>() {
    protected var mAgentWeb: AgentWeb? = null
    private var mMiddleWareWebChrome: MiddlewareWebChromeBase? = null
    private var mMiddleWareWebClient: MiddlewareWebClientBase? = null
    private var mErrorLayoutEntity: ErrorLayoutEntity? = null
    protected val agentWebUIController: AgentWebUIControllerImplBase? = null

    protected abstract fun getAgentWebParent(): ViewGroup
    protected abstract fun getUrl(): String?

    private val agentWebSettings: IAgentWebSettings<*> = AgentWebSettingsImpl.getInstance()

    protected val webChromeClient: WebChromeClient? = null

    protected abstract val indicatorColor: Int

    protected abstract val indicatorHeight: Int

    protected val webViewClient: WebViewClient? = null

    protected val webView: WebView? = null

    protected val webLayout: IWebLayout<*, *>? = null

    protected val permissionInterceptor: PermissionInterceptor? = null

    protected val middleWareWebChrome: MiddlewareWebChromeBase
        get() {
            if (mMiddleWareWebChrome == null) {
                mMiddleWareWebChrome = object : MiddlewareWebChromeBase() {
                    override fun onReceivedTitle(view: WebView, title: String) {
                        super.onReceivedTitle(view, title)
                        setTitle(view, title)
                    }
                }
            }

            return mMiddleWareWebChrome!!
        }

    protected val middleWareWebClient: MiddlewareWebClientBase
        get() {
            if (mMiddleWareWebClient == null) {
                mMiddleWareWebClient = object : MiddlewareWebClientBase() {

                }
            }
            return mMiddleWareWebClient!!
        }

    override fun initView() {
        super.initView()

        val mErrorLayoutEntity = getErrorLayoutEntity()
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(
                getAgentWebParent(),
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
            .useDefaultIndicator(indicatorColor, indicatorHeight)
            .setWebView(webView)
            .setWebLayout(webLayout)
            .setAgentWebWebSettings(agentWebSettings)
            .setWebViewClient(webViewClient)
            .setPermissionInterceptor(permissionInterceptor)
            .setWebChromeClient(webChromeClient)
            .interceptUnkownUrl()
            .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
            .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
            .setAgentWebUIController(agentWebUIController)
            .setMainFrameErrorView(mErrorLayoutEntity.layoutRes, mErrorLayoutEntity.reloadId)
            .useMiddlewareWebChrome(middleWareWebChrome)
            .useMiddlewareWebClient(middleWareWebClient)
            .createAgentWeb()
            .ready()
            .go(getUrl())
    }

    private fun getErrorLayoutEntity(): ErrorLayoutEntity {
        if (this.mErrorLayoutEntity == null) {
            this.mErrorLayoutEntity = ErrorLayoutEntity()
        }
        return mErrorLayoutEntity!!
    }

    protected open fun setTitle(view: WebView, title: String) {

    }

    private inner class ErrorLayoutEntity {
        @JvmField
        var layoutRes = R.layout.agentweb_error_page

        @JvmField
        var reloadId: Int = 0

        fun setLayoutRes(layoutRes: Int) {
            var layoutRes = layoutRes
            this.layoutRes = layoutRes
            if (layoutRes <= 0) {
                layoutRes = -1
            }
        }

        fun setReloadId(reloadId: Int) {
            var reloadId = reloadId
            this.reloadId = reloadId
            if (reloadId <= 0) {
                reloadId = -1
            }
        }
    }

    override fun onPause() {
        if (mAgentWeb != null) {
            mAgentWeb!!.webLifeCycle.onPause()
        }
        super.onPause()

    }

    override fun onResume() {
        if (mAgentWeb != null) {
            mAgentWeb!!.webLifeCycle.onResume()
        }
        super.onResume()
    }

    override fun onDestroy() {
        if (mAgentWeb != null) {
            mAgentWeb!!.webLifeCycle.onDestroy()
        }
        super.onDestroy()
    }
}
