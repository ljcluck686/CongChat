package com.software.mywechat.core.network.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.software.mywechat.MyApplication
import com.software.mywechat.core.config.Config
import com.software.mywechat.core.network.datasource.MyNetworkDataSource
import com.software.mywechat.core.network.datasource.MyRetrofitDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS) //连接超时时间
            .writeTimeout(10, TimeUnit.SECONDS) //写超时时间
            .readTimeout(10, TimeUnit.SECONDS) //读超时时间
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                setLevel(
                    if (Config.DEBUG)
                        HttpLoggingInterceptor.Level.BODY
                    else
                        HttpLoggingInterceptor.Level.NONE
                )
            }).apply {
                //添加chucker实现应用内显示网络请求信息拦截器
                if (Config.DEBUG) {
                    addInterceptor(ChuckerInterceptor.Builder(MyApplication.instance).build())
                }
            }
            .build()

    }

    @Provides
    @Singleton
    fun providesCallFactory(okHttpClient: OkHttpClient): Call.Factory = okHttpClient
}