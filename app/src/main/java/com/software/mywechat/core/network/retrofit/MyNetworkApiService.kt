package com.software.mywechat.core.network.retrofit

import com.software.mywechat.core.model.DataListWrapper
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
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

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

    @PATCH("v1/user/update")
    suspend fun update(
        @Header("Authorization") authorization: String,
        @Body data: UpdateReq,
    ):NetworkResponse<Info>

    @GET("v1/user/findUser")
    suspend fun findUser(
        @Query("name") name:String ="点",
        @Query("phone") phone:String="1",
        @Query("ids") ids:String ="1"
    ): NetworkResponse<Infos>

    @GET("v1/social/firend/getFriendList")
    suspend fun getFriendList(
        @Query("user_id") userId:String
    ): NetworkResponse<Info>

    @POST("v1/social/firend/applyFriend")
    suspend fun applyFriend(
        @Body data:FriendApplyRequest
    ):NetworkResponse<FriendApplyResponse>

    @GET("v1/social/firend/getFriendApplyList")
    suspend fun getFriendApplyList(
        @Query("user_id") userId: String
    ):NetworkResponse<DataListWrapper>

    @GET("v1/social/firend/getHandleFriendApplyList")
    suspend fun getHandleFriendApplyList(
        @Query("targetId") targetId:String
    ):NetworkResponse<DataListWrapper>

}