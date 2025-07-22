package com.software.mywechat.core.network.datasource

import com.software.mywechat.core.model.Info
import com.software.mywechat.core.model.response.NetworkResponse
import com.software.mywechat.core.model.Session
import com.software.mywechat.core.model.User
import com.software.mywechat.core.model.UserInfo

interface MyNetworkDataSource {
    suspend fun register(
        data: User,
    ): NetworkResponse<Session>

    suspend fun login(
        data: User
    ):NetworkResponse<Session>

    suspend fun userInfo(
        authorization: String
    ):NetworkResponse<Info>

}