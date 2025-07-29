package com.software.mywechat.core.data.repository

import com.software.mywechat.core.model.DataListWrapper
import com.software.mywechat.core.model.FriendApplyRequest
import com.software.mywechat.core.model.FriendApplyResp
import com.software.mywechat.core.model.FriendApplyResponse
import com.software.mywechat.core.model.Info
import com.software.mywechat.core.model.Infos
import com.software.mywechat.core.model.Session
import com.software.mywechat.core.model.UpdateReq
import com.software.mywechat.core.model.User
import com.software.mywechat.core.model.UserInfo
import com.software.mywechat.core.model.response.NetworkResponse
import com.software.mywechat.core.network.datasource.MyNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

    fun login(
        data:User
    ):Flow<NetworkResponse<Session>> = flow{
        emit(
            networkDataSource.login(data)
        )
    }.flowOn(Dispatchers.IO)

    fun userInfo(
        authorization: String
    ):Flow<NetworkResponse<Info>> = flow{
        emit(
            networkDataSource.userInfo(authorization)
        )
    }.flowOn(Dispatchers.IO)

    fun update(
        authorization: String,
        data:UpdateReq,
    ):Flow<NetworkResponse<Info>> = flow {
        emit(
            networkDataSource.update(authorization,data)
        )
    }.flowOn(Dispatchers.IO)

    fun getFriendList(
        userId:String
    ):Flow<NetworkResponse<Info>> = flow {
        emit(
            networkDataSource.getFriendList(userId)
        )
    }.flowOn(Dispatchers.IO)

    fun findUser(
        name: String = "点",
        phone: String = "1",
        ids: String = "1"
    ):Flow<NetworkResponse<Infos>> = flow {
        emit(
            networkDataSource.findUser(name, phone, ids)
        )
    }.flowOn(Dispatchers.IO)

    fun applyFriend(
        data:FriendApplyRequest
    ):Flow<NetworkResponse<FriendApplyResponse>> = flow{
        emit(
            networkDataSource.applyFriend(data)
        )
    }.flowOn(Dispatchers.IO)

    fun getFriendApplyList(
        userId: String
    ):Flow<NetworkResponse<DataListWrapper>> = flow {
        emit(
            networkDataSource.getFriendApplyList(userId)
        )
    }.flowOn(Dispatchers.IO)

    fun getHandleFriendApplyList(
        targetId:String
    ):Flow<NetworkResponse<DataListWrapper>> = flow {
        emit(
            networkDataSource.getHandleFriendApplyList(targetId)
        )
    }.flowOn(Dispatchers.IO)

}