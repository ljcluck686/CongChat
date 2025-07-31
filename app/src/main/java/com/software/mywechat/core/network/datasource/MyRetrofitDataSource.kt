package com.software.mywechat.core.network.datasource

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.software.mywechat.core.config.Config
import com.software.mywechat.core.model.DataListWrapper
import com.software.mywechat.core.model.FriendApplyAction
import com.software.mywechat.core.model.FriendApplyRequest
import com.software.mywechat.core.model.FriendApplyResp
import com.software.mywechat.core.model.FriendApplyResponse
import com.software.mywechat.core.model.Info
import com.software.mywechat.core.model.Infos
import com.software.mywechat.core.model.response.NetworkResponse
import com.software.mywechat.core.model.Session
import com.software.mywechat.core.model.UpdateReq
import com.software.mywechat.core.model.User
import com.software.mywechat.core.model.UserInfo
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

    override suspend fun userInfo(authorization: String): NetworkResponse<Info> {
        return service.userInfo(authorization)
    }

    override suspend fun update(authorization: String, data: UpdateReq): NetworkResponse<Info> {
        return service.update(authorization,data)
    }

    override suspend fun getFriendList(userId: String): NetworkResponse<Info> {
        return service.getFriendList(userId)
    }

    override suspend fun applyFriend(data: FriendApplyRequest): NetworkResponse<FriendApplyResponse> {
        return service.applyFriend(data)
    }

    override suspend fun handleFriendApply(data: FriendApplyAction) {
        return service.handleFriendApply(data)
    }

    override suspend fun findUser(
        name:String ,
        phone:String,
        ids:String ,
    ): NetworkResponse<Infos>  {
        return service.findUser(name,phone,ids)
    }

    override suspend fun getFriendApplyList(userId: String): NetworkResponse<DataListWrapper> {
        return service.getFriendApplyList(userId)
    }

    override suspend fun getHandleFriendApplyList(targetId: String): NetworkResponse<DataListWrapper> {
        return service.getHandleFriendApplyList(targetId)
    }
}