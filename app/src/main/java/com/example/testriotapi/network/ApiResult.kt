package com.example.testriotapi.network

import com.example.testriotapi.model.ReturnModel
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * @author Daewon
 * @package com.example.testriotapi.network
 * @email green201402317@gmail.com
 * @created 2021/12/30
 */

/**
 * api 응답에 관한 기본 모델
 */
sealed class ApiResult<out T> {

    data class Success<T>(private val response: Response<T>) : ApiResult<T>() {
        val res: Response<T> = response
        val data: T? = response.body()
    }

    data class Error<T>(private val response: Response<T>?, private val t: Throwable? = null, private val responseParsing: ReturnModel<*>? = null) : ApiResult<T>() {
        val res: Response<T>? = response
        val errorBody: ResponseBody? = response?.errorBody()
        val throwable: Throwable? = t
        var resParsing: ReturnModel<*>? = responseParsing
    }

    companion object {
        private val httpSuccessCode: IntRange = 200..299
        fun <T> parsing(response: Response<T>): ApiResult<T> {
            return try {
                if (response.raw().code in httpSuccessCode && response.isSuccessful) {
                    Success(response)
                } else {
                    Error(response)
                }
            } catch (e: Exception) {

                Error(null)
            }
        }
    }
}