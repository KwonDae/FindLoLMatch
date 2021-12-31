package com.example.testriotapi.Common

/**
 * @author Daewon
 * @package com.example.testriotapi.Common
 * @email green201402317@gmail.com
 * @created 2021/12/29
 */

object Constants {

    const val TAG : String = "로그"
    val BASE_URL = "https://kr.api.riotgames.com/lol/"
    val API_KEY = "RGAPI-8fa19435-7697-4e2e-88be-6a3a0dd7dd39"
}

enum class RESPONSE_STATUS{
    OKAY,
    FAIL,
    NO_CONTENT
}