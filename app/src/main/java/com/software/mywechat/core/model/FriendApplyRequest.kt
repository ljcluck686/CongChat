package com.software.mywechat.core.model

import kotlinx.serialization.Serializable

@Serializable
data class FriendApplyRequest(
    val applicantId: String = "1",
    val targetId: String = "1",
    val greetMsg: String = "1"
)
