package com.example.testriotapi.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * @author Daewon
 * @package com.example.testriotapi.di
 * @email green201402317@gmail.com
 * @created 2021/12/29
 */

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

//    @Provides
//    @ActivityRetainedScoped
//    fun provideSummonerRepository(service: ApiSummonerService, pref: PreferenceManager): SummonerRepository {
//        return SummonerRepository(service, pref)
//    }
}