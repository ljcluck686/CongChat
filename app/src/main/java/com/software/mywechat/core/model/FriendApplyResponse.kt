package com.software.mywechat.core.model

import kotlinx.serialization.Serializable

@Serializable
data class FriendApplyResponse(
    val applyId: String,
    val applyTime: Long
)
