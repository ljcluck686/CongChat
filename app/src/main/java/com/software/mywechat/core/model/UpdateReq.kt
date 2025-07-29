package com.software.mywechat.core.model

import kotlinx.serialization.Serializable

@Serializable
data class UpdateReq(
    val nickname:String,
    val avatar:String,
    val sex:Int,
)
