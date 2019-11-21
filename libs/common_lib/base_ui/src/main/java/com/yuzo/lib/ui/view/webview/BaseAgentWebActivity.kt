package com.yuzo.lib.ui.view.webview

import android.content.Intent
import android.view.KeyEvent
import android.view.ViewGroup
import android.webkit.WebView
import androidx.databinding.ViewDataBinding
import com.just.agentweb.*
import com.yuzo.lib.ui.activity.BaseActivity

/**
 * Author: yuzo
 * Date: 2019-11-21
 */
abstract class BaseAgentWebActivity<T : ViewDataBinding> : BaseActivity<T>() {
    protected var agentWeb: AgentWeb? = null
    private var mErrorLayoutEntity: ErrorLayoutEntity? = null
    private var mMiddleWareWebChrome: MiddlewareWebChromeBase? = null
    private var mMiddleWareWebClient: MiddlewareWebClientBase? = null

    private val agentWebSettings: IAgentWebSettings<*> = AgentWebSettingsImpl.getInstance()

    protected val webChromeClient: WebChromeClient? = null

    protected abstract fun getAgentWebParent(): ViewGroup
    protected abstract fun getUrl(): String

    protected abstract val indicatorColor: Int

    protected abstract val indicatorHeight: Int

    protected val webViewClient: WebViewClient? = null

    protected val webView: WebView? = null

    protected val webLayout: IWebLayout<*, *>? = null

    protected val permissionInterceptor: PermissionInterceptor? = null

    val agentWebUIController: AgentWebUIControllerImplBase? = null

    val openOtherAppWay: DefaultWebClient.OpenOtherPageWays? = null

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

    override fun initContainerView(binding: T) {
        super.initContainerView(binding)
        val mErrorLayoutEntity = getErrorLayoutEntity()
        agentWeb = AgentWeb.with(this)
            .setAgentWebParent(getAgentWebParent(), ViewGroup.LayoutParams(-1, -1))
            .useDefaultIndicator(indicatorColor, indicatorHeight)
            .setWebChromeClient(webChromeClient)
            .setWebViewClient(webViewClient)
            .setWebView(webView)
            .setPermissionInterceptor(permissionInterceptor)
            .setWebLayout(webLayout)
            .setAgentWebUIController(agentWebUIController)
            .interceptUnkownUrl()
            .setOpenOtherPageWays(openOtherAppWay)
            .useMiddlewareWebChrome(middleWareWebChrome)
            .useMiddlewareWebClient(middleWareWebClient)
            .setAgentWebWebSettings(agentWebSettings)
            .setMainFrameErrorView(mErrorLayoutEntity.layoutRes, mErrorLayoutEntity.reloadId)
            .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
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

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (agentWeb != null && agentWeb!!.handleKeyEvent(keyCode, event)) {
            true
        } else {
            super.onKeyDown(keyCode, event)
        }
    }

    override fun onClickHeaderLeft() {
        if (agentWeb != null && !agentWeb!!.back()){
            super.onClickHeaderLeft()
        }
    }

    override fun onPause() {
        if (agentWeb != null) {
            agentWeb!!.webLifeCycle.onPause()
        }
        super.onPause()

    }

    override fun onResume() {
        if (agentWeb != null) {
            agentWeb!!.webLifeCycle.onResume()
        }
        super.onResume()
    }

    override fun onDestroy() {
        if (agentWeb != null) {
            agentWeb!!.webLifeCycle.onDestroy()
        }
        super.onDestroy()
    }

    protected open fun setTitle(view: WebView, title: String) {}
}
