package com.software.mywechat.core.network.retrofit

import com.software.mywechat.core.model.response.NetworkResponse
import com.software.mywechat.core.model.Session
import com.software.mywechat.core.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface MyNetworkApiService {
    @POST("v1/user/register")
    suspend fun register(
        @Body data: User,
    ): NetworkResponse<Session>


}