package com.software.mywechat.core.network.datasource

import com.software.mywechat.core.model.response.NetworkResponse
import com.software.mywechat.core.model.Session
import com.software.mywechat.core.model.User

interface MyNetworkDataSource {
    suspend fun register(
        data: User,
    ): NetworkResponse<Session>

    suspend fun login(
        data: User
    ):NetworkResponse<Session>
}