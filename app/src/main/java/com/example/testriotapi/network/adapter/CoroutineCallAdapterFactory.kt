package com.mhows.reple.network.adapter

import com.example.testriotapi.network.ApiResult
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * retrofit coroutine error handling 을 위한 adapter들
 */
class CoroutineCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        if (getRawType(returnType) == Call::class.java) {
            val callType = getParameterUpperBound(0, returnType as ParameterizedType)
            if (getRawType(callType) == ApiResult::class.java) {
                val resultType = getParameterUpperBound(0, callType as ParameterizedType)
                return CoroutineCallAdapter(resultType)
            }
        }

        return null
    }
}
