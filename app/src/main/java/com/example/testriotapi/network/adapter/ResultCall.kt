package com.mhows.reple.network.adapter

import com.example.testriotapi.network.ApiResult
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * retrofit coroutine error handling 을 위한 adapter들
 */
class ResultCall<T>(call: Call<T>) : CallDelegate<T, ApiResult<T>>(call) {

    override fun enqueue(callback: Callback<ApiResult<T>>) {
        val totalRetries = 3
        var retryCount = 0
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val apiResponse = ApiResult.parsing(response)
                callback.onResponse(this@ResultCall, Response.success(apiResponse))

            }

            override fun onFailure(call: Call<T>, t: Throwable) {

//               /* 크래쉬 의심되서 일단 막음 */
//                if (t is SocketTimeoutException && call.request().method.equals("GET")) {
//                if (call.request().method.equals("GET")) {
//                    if (retryCount++ < totalRetries) {
//                        call.clone().enqueue(this)
//                    } else {
//                        callback.onResponse(this@ResultCall, Response.success(ApiResult.Error(null, t)))
//                    }
//                } else {
                callback.onResponse(this@ResultCall, Response.success(ApiResult.Error(null, t)))
//                }
            }
        })
    }

    override fun clone() = ResultCall(call.clone())
    override fun timeout() = Timeout.NONE
}
