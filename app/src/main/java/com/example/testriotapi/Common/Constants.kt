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

    const val SHARED_PREFERENCES_NAME = "sharedPref"

    val BASE_URL = "https://kr.api.riotgames.com/lol/"
    val API_KEY = "RGAPI-6e29dc31-d150-4db7-b7b9-03872656d680"
}

enum class RESPONSE_STATUS{
    OKAY,
    FAIL,
    NO_CONTENT
}