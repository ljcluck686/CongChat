package com.software.mywechat.core.model


import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable



@Serializable
data class User(
    val phone:String,
    val nickname:String,
    val password:String,
    var avatar:String,
    val sex:Byte,
)
