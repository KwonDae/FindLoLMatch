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
    val API_KEY = "RGAPI-f030bcb7-3ae9-46c5-ade2-2934a5c3ac7e"
}

enum class RESPONSE_STATUS{
    OKAY,
    FAIL,
    NO_CONTENT
}