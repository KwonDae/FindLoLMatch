package com.example.testriotapi.di

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.testriotapi.BaseApplication
import com.example.testriotapi.BuildConfig
import com.example.testriotapi.Common.Constants
import com.example.testriotapi.network.ApiSummonerService
import com.example.testriotapi.util.PreferenceManager
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mhows.reple.network.adapter.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author Daewon
 * @package com.example.testriotapi.di
 * @email green201402317@gmail.com
 * @created 2021/12/29
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    
    private val BASE_URL = Constants.BASE_URL

    private const val CONNECT_TIMEOUT = 15L
    private const val WRITE_TIMEOUT = 15L
    private const val READ_TIMEOUT = 15L
    
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()
    
    @Singleton
    @Provides
    fun provideOkHttpClient(pref: PreferenceManager): OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        addInterceptor { chain ->
            val original = chain.request()
            val builder = original.newBuilder()

            builder.addHeader("X-Riot-Token", Constants.API_KEY)
            builder.method(original.method, original.body)
            val request = builder.build()

            val response = chain.proceed(request)

            if(response.code != 200) {
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(BaseApplication.instance, "${response.code} 에러 입니다.", Toast.LENGTH_SHORT).show()
                }
            }
            return@addInterceptor response
        }
        addInterceptor(
            HttpLoggingInterceptor().apply {
                if(BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            }
        )
    }.build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder().apply {
        baseUrl(BASE_URL)
        addConverterFactory(GsonConverterFactory.create(gson))
        addCallAdapterFactory(CoroutineCallAdapterFactory())
        client(okHttpClient)
    }.build()

    @Singleton
    @Provides
    fun provideApiSummonerService(retrofit: Retrofit): ApiSummonerService = retrofit.create(ApiSummonerService::class.java)
}