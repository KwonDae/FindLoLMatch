package com.example.testriotapi.Common

/**
 * @author Daewon
 * @package com.example.testriotapi.Common
 * @email green201402317@gmail.com
 * @created 2021/12/29
 */

object Constants {

    const val TAG : String = "로그"
    const val USER_DATABASE_NAME = "user_db"

    val BASE_URL = "https://kr.api.riotgames.com/lol/"
    val API_KEY = "RGAPI-5a2bfd88-edce-4f1e-bf32-26ff2aaa465e"
}

enum class RESPONSE_STATUS{
    OKAY,
    FAIL,
    NO_CONTENT
}