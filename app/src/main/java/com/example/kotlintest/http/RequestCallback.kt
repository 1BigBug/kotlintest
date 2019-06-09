package com.example.kotlintest.http

import android.content.Context
import com.example.kotlintest.Beens.BaseBeens
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class RequestCallback<T>(private val context: Context) : Observer<BaseBeens<T>> {

    abstract fun success(data: T)
    abstract fun failure(statusCode: Int, apiErrorModel: ApiErrorModel)

    private object Status {
        const val SUCCESS = 200
    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(t: BaseBeens<T>) {
        if (t.state == Status.SUCCESS) {
            success(t.content)
            return
        }

        val apiErrorModel: ApiErrorModel = when (t.state) {
            ApiErrorType.INTERNAL_SERVER_ERROR.code ->
                ApiErrorType.INTERNAL_SERVER_ERROR.getApiErrorModel(context)
            ApiErrorType.BAD_GATEWAY.code ->
                ApiErrorType.BAD_GATEWAY.getApiErrorModel(context)
            ApiErrorType.NOT_FOUND.code ->
                ApiErrorType.NOT_FOUND.getApiErrorModel(context)
            else -> ApiErrorModel(t.state, t.alertMessage)
        }
        failure(t.state, apiErrorModel)
    }

    override fun onComplete() {
    }

    override fun onError(e: Throwable) {
        println(e)
        val apiErrorType: ApiErrorType = when (e) {
            is UnknownHostException -> ApiErrorType.NETWORK_NOT_CONNECT
            is ConnectException -> ApiErrorType.NETWORK_NOT_CONNECT
            is SocketTimeoutException -> ApiErrorType.CONNECTION_TIMEOUT
            else -> ApiErrorType.UNEXPECTED_ERROR
        }
        failure(apiErrorType.code, apiErrorType.getApiErrorModel(context))
    }
}