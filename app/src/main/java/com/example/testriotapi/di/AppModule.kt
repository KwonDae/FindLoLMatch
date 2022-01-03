package com.example.testriotapi.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.testriotapi.Common.Constants
import com.example.testriotapi.Common.Constants.USER_DATABASE_NAME
import com.example.testriotapi.db.UserDAO
import com.example.testriotapi.db.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Daewon
 * @package com.example.testriotapi.di
 * @email green201402317@gmail.com
 * @created 2021/12/29
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideUserDatabase(
        @ApplicationContext app: Context
    ): UserDatabase = Room.databaseBuilder(
        app,
        UserDatabase::class.java,
        USER_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideUserDao(db: UserDatabase): UserDAO = db.getUserDao()
}