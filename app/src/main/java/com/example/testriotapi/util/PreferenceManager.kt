package com.example.testriotapi.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * SharedPreferences
 */
class PreferenceManager @Inject constructor(private val pref: SharedPreferences) {
//    private var pref = context.getSharedPreferences(KEY_PREFERENCE, Activity.MODE_PRIVATE)

    companion object {

        const val KEY_IS_OPENED_INTRO_GUIDE = "IS_OPENED_INTRO_GUIDE"
        const val KEY_NOT_DISPLAY_MAIN_POP_DATE = "DISPLAY_MAIN_POP_DATE"
        const val IS_OPENED_PERMISSION = "IS_OPENED_PERMISSION"

        private const val AUTO_LOGIN = "AUTO_LOGIN"

        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val REFRESH_TOKEN = "REFRESH_TOKEN"

        private const val FCM_PUSH_TOKEN = "FCM_PUSH_TOKEN"
    }

    /**
     * 프리퍼런스 저장
     *
     * @param context
     * @param key
     * @param value
     * */
    fun setPreference(key: String, value: Any) {
        pref.edit {
            if (value is String) {
                putString(key, value)
            }

            if (value is Int) {
                putInt(key, value)
            }

            if (value is Long) {
               putLong(key, value)
            }

            if (value is Float) {
                putFloat(key, value)
            }

            if (value is Boolean) {
                putBoolean(key, value)
            }
        }
    }

    /**
     * 프리퍼런스 가져오기
     *
     * @param context
     * @param key
     * */
    fun getPreference(key: String): Any? {
        var m = pref.all
        return m[key]
    }

    /**
     * 프리퍼런스 가져오기
     *
     * @param context
     * @param key
     * */
    fun getPreference(key: String, defaultValue: Any): Any? {
        var m = pref.all
        return m[key] ?: defaultValue
    }

}
