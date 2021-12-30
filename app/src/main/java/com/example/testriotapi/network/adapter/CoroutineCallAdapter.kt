package com.mhows.reple.network.adapter

import com.example.testriotapi.network.ApiResult
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**
 * retrofit coroutine error handling 을 위한 adapter들
 */
class CoroutineCallAdapter constructor(private val resultType: Type) : CallAdapter<Type, Call<ApiResult<Type>>> {

    override fun responseType(): Type {
        return resultType
    }

    override fun adapt(call: Call<Type>): Call<ApiResult<Type>>? {
        return ResultCall(call)
    }
}
