package com.software.mywechat.core.network.retrofit

import com.software.mywechat.core.model.Info
import com.software.mywechat.core.model.response.NetworkResponse
import com.software.mywechat.core.model.Session
import com.software.mywechat.core.model.User
import com.software.mywechat.core.model.UserInfo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MyNetworkApiService {
    @POST("v1/user/register")
    suspend fun register(
        @Body data: User,
    ): NetworkResponse<Session>

    @POST("v1/user/login")
    suspend fun login(
        @Body data:User,
    ):NetworkResponse<Session>

    @GET("v1/user/userinfo")
    suspend fun userInfo(
        @Header("Authorization") authorization: String
    ): NetworkResponse<Info>

}