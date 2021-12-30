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
    val API_KEY = "RGAPI-a2a09768-b2b9-422d-9384-65cf73803673"
}

enum class RESPONSE_STATUS{
    OKAY,
    FAIL,
    NO_CONTENT
}