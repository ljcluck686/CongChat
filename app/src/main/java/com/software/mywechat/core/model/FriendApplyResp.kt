package com.software.mywechat.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FriendApplyResp(
    @SerialName("user_id")
    val userId :String,
    @SerialName("nickname")
    val nickname:String ,
    @SerialName("avatar_url")
    val avatarUrl :String ,
    @SerialName("gender")
    val  gender :Int ,
    @SerialName("greet_msg")
    val greetMsg  :String ,
    @SerialName("status")
    val status : Int,
)