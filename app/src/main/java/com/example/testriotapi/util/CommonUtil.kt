package com.example.testriotapi.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Daewon
 * @package com.example.testriotapi.util
 * @email green201402317@gmail.com
 * @created 2022/01/02
 */

object CommonUtil {

    /**
     * yy.MM.dd 날짜 가져오기
     */
    fun getDateFormat() : String {
        return SimpleDateFormat("yy.MM.dd", Locale.KOREA).format(Date())
    }
}