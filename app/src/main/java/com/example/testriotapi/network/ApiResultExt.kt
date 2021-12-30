package com.example.testriotapi.network

/**
 * api 응답에 관한 기본 모델(확장 클리스)
 */

fun <T> ApiResult<T>.onSuccess(onResult: ApiResult.Success<T>.() -> Unit): ApiResult<T> {
    if (this is ApiResult.Success) {
        onResult(this)
    }
    return this
}

fun <T> ApiResult<T>.onError(onResult: ApiResult.Error<T>.() -> Unit): ApiResult<T> {
    if (this is ApiResult.Error) {
        onResult(this)
    }
    return this
}
