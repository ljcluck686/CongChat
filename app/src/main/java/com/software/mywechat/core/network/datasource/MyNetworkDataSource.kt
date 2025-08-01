package com.software.mywechat.core.network.datasource

import com.software.mywechat.core.model.DataListWrapper
import com.software.mywechat.core.model.FriendApplyAction
import com.software.mywechat.core.model.FriendApplyRequest
import com.software.mywechat.core.model.FriendApplyResp
import com.software.mywechat.core.model.FriendApplyResponse
import com.software.mywechat.core.model.FriendList
import com.software.mywechat.core.model.Info
import com.software.mywechat.core.model.Infos
import com.software.mywechat.core.model.response.NetworkResponse
import com.software.mywechat.core.model.Session
import com.software.mywechat.core.model.UpdateReq
import com.software.mywechat.core.model.User
import retrofit2.http.Body
import retrofit2.http.Header


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

    suspend fun getFriendList(
        userId:String
    ):NetworkResponse<FriendList>

    suspend fun applyFriend(
        data: FriendApplyRequest
    ):NetworkResponse<FriendApplyResponse>

    suspend fun handleFriendApply(
        data:FriendApplyAction
    )

    suspend fun findUser(
        name: String = "点",
        phone: String = "1",
        ids: String = "1"
    ):NetworkResponse<Infos>

    suspend fun getFriendApplyList(
        userId:String
    ):NetworkResponse<DataListWrapper>

    suspend fun getHandleFriendApplyList(
        targetId:String
    ):NetworkResponse<DataListWrapper>

    suspend fun update(
        authorization: String,
        data: UpdateReq,
    ):NetworkResponse<Info>

}