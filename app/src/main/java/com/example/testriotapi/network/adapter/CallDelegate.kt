package com.mhows.reple.network.adapter

import okhttp3.Request
import retrofit2.Call
import retrofit2.Response

/**
 * retrofit coroutine error handling 을 위한 adapter들
 */
abstract class CallDelegate<TIn, TOut>(
    protected val call: Call<TIn>
) : Call<TOut> {
    override fun execute(): Response<TOut> = throw NotImplementedError()

    override fun cancel() = call.cancel()
    override fun request(): Request = call.request()
    override fun isExecuted() = call.isExecuted
    override fun isCanceled() = call.isCanceled
}
