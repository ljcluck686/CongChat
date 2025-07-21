package com.software.mywechat.core.network.datasource

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.software.mywechat.core.config.Config
import com.software.mywechat.core.model.response.NetworkResponse
import com.software.mywechat.core.model.Session
import com.software.mywechat.core.model.User
import com.software.mywechat.core.network.retrofit.MyNetworkApiService
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyRetrofitDataSource @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
) :MyNetworkDataSource{

    private val service = Retrofit.Builder()
        .baseUrl(Config.ENDPOINT)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType())
        )
        .build()
        .create(MyNetworkApiService::class.java)

    override suspend fun register(data: User): NetworkResponse<Session> {
        return service.register(data)
    }

    override suspend fun login(data: User): NetworkResponse<Session> {
        return service.login(data)
    }

    override suspend fun userInfo(authorization: String): NetworkResponse<User> {
        return service.userInfo(authorization)
    }
}