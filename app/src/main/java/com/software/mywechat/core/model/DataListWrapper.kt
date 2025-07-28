package com.software.mywechat.core.model

import kotlinx.serialization.Serializable

@Serializable
data class DataListWrapper(
    val list: List<FriendApplyResp>
)
