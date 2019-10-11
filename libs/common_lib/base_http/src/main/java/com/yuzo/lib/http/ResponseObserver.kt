package com.yuzo.lib.http

import com.yuzo.lib.log.v
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException

/**
 * Author: yuzo
 * Date: 2019-09-29
 */
abstract class ResponseObserver<T> : Observer<T> {
    abstract fun onSuccess(response: T?)

    abstract fun onError(code: Int, message: String)

    override fun onComplete() {
        v(TAG, "onComplete")
    }

    override fun onSubscribe(d: Disposable) {
        v(TAG, "onSubscribe")
    }

    override fun onNext(value: T) {
        v(TAG, "onNext")

        onSuccess(value)
    }

    override fun onError(e: Throwable) {
        v(TAG, "onError is $e")

        if (e is HttpException) {
            e.apply {
                onError(code(), message())
            }
        } else {
            onError(-1, e.message?:" unknown error")
        }
    }

    companion object{
        private const val TAG = "ResponseObserver"
    }
}