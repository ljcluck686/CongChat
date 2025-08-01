package com.software.mywechat.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FriendList(
    @SerialName("friend_list")
    val friendList:List<User>
) {
}