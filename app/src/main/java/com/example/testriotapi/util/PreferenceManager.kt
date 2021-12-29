package com.example.testriotapi.util

import android.app.Activity
import android.content.Context
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * SharedPreferences
 */
class PreferenceManager @Inject constructor(private val context: Context) {
    private var pref = context.getSharedPreferences(KEY_PREFERENCE, Activity.MODE_PRIVATE)

    companion object {

        const val KEY_PREFERENCE = "AppConfigData"
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
        val pref = context.getSharedPreferences(KEY_PREFERENCE, Activity.MODE_PRIVATE)
        val editor = pref.edit()

        if (value is String) {
            editor.putString(key, value)
        }

        if (value is Int) {
            editor.putInt(key, value)
        }

        if (value is Long) {
            editor.putLong(key, value)
        }

        if (value is Float) {
            editor.putFloat(key, value)
        }

        if (value is Boolean) {
            editor.putBoolean(key, value)
        }

        editor.apply()
    }

    /**
     * 프리퍼런스 가져오기
     *
     * @param context
     * @param key
     * */
    fun getPreference(key: String): Any? {
        val pref = context.getSharedPreferences(KEY_PREFERENCE, Activity.MODE_PRIVATE) ?: return null
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
        val pref = context.getSharedPreferences(KEY_PREFERENCE, Activity.MODE_PRIVATE) ?: return defaultValue
        var m = pref.all
        return m[key] ?: defaultValue
    }

    /**
     * 튜토리얼 여부
     * */
    var isOpendIntroGuide: Boolean
        get() = pref.getBoolean(KEY_IS_OPENED_INTRO_GUIDE, false)
        set(flag) {
            pref.edit().putBoolean(KEY_IS_OPENED_INTRO_GUIDE, flag).apply()
        }

    /**
     * 오늘 메인 팝업 표시  조회
     * */
    fun isTodayMainPopdisplay(popNo: String): Boolean {
        val todayDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
//        Dlog.v("todaydate = " + todayDate)
        val getPopData = pref.getString(KEY_NOT_DISPLAY_MAIN_POP_DATE + "_" + popNo, "")
//        Dlog.v("getPopData = " + getPopData)

        return todayDate.equals(getPopData)
    }

    /**
     * 오늘 메인 팝업 표시 설정
     * */
    fun setTodayMainPopdisplay(popNo: String) {
        val todayDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
//        Dlog.v("todaydate = " + todayDate)
        pref.edit().putString(KEY_NOT_DISPLAY_MAIN_POP_DATE + "_" + popNo, todayDate).apply()
    }

    /**
     * 퍼미션 여부
     * */
    var isOpendPermission: Boolean
        get() = pref.getBoolean(IS_OPENED_PERMISSION, false)
        set(flag) {
            pref.edit().putBoolean(IS_OPENED_PERMISSION, flag).apply()
        }

    // var ACCESS_TOKEN: com.mhows.reple.data.local.SharedPreferencesUtil.Key? = null,
    // var REFRESH_TOKEN: com.mhows.reple.data.local.SharedPreferencesUtil.Key? = null

    var isAutoLogin: Boolean
        get() = pref.getBoolean(AUTO_LOGIN, false)
        set(flag) {
            pref.edit().putBoolean(AUTO_LOGIN, flag).apply()
        }

    var accessToken: String
        get() = pref.getString(ACCESS_TOKEN, "")?.let {
            pref.getString(ACCESS_TOKEN, "")
        } ?: run {
            ""
        }
        set(value) {
            pref.edit().putString(ACCESS_TOKEN, value).apply()
        }

    var refreshToken: String
        get() = pref.getString(REFRESH_TOKEN, "")?.let {
            pref.getString(REFRESH_TOKEN, "")
        } ?: run {
            ""
        }
        set(value) {
            pref.edit().putString(REFRESH_TOKEN, value).apply()
        }

    var fcmToken: String
        get() = pref.getString(FCM_PUSH_TOKEN, "")?.let {
            pref.getString(FCM_PUSH_TOKEN, "")
        } ?: run {
            ""
        }
        set(value) {
            pref.edit().putString(FCM_PUSH_TOKEN, value).apply()
        }
}
