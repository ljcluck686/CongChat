package com.software.mywechat.core.data.repository

import com.software.mywechat.core.model.Session
import com.software.mywechat.core.model.User
import com.software.mywechat.core.model.response.NetworkResponse
import com.software.mywechat.core.network.datasource.MyNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val networkDataSource: MyNetworkDataSource
){
    fun register(
        data:User
    ): Flow<NetworkResponse<Session>> = flow{
        emit(
            networkDataSource.register(data)
        )
    }.flowOn(Dispatchers.IO)


}