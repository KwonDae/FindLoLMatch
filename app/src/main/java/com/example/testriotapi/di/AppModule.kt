package com.example.testriotapi.di

import android.content.Context
import androidx.room.Room
import com.example.testriotapi.Common.Constants.USER_DATABASE_NAME
import com.example.testriotapi.db.UserDatabase
import com.example.testriotapi.util.PreferenceManager
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
    fun provideSharedPreference(@ApplicationContext context: Context): PreferenceManager {
        return PreferenceManager(context)
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
    fun provideUserDao(db: UserDatabase) = db.getUserDao()
}