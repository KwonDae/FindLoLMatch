package com.example.testriotapi.model

data class ReturnModel<T>(
    val resCode: Int,
    val resMsg: String,
    var data: T? = null
)
