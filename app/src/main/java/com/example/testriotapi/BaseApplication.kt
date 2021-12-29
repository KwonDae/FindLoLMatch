package com.example.testriotapi

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * @author Daewon
 * @package com.example.testriotapi
 * @email green201402317@gmail.com
 * @created 2021/12/29
 */

@HiltAndroidApp
class BaseApplication : Application() {

    companion object {
        lateinit var instance: BaseApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}