package com.software.mywechat.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FriendApplyAction(
    @SerialName("applicant_id")
    val applicantId: String = "1",

    @SerialName("target_id")
    val targetId: String = "1",

    @SerialName("is_approved")
    val isApproved : Boolean = false,
)
