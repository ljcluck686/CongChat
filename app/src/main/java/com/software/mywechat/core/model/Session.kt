package com.software.mywechat.core.model

import kotlinx.serialization.Serializable


@Serializable
data class Session(
    val id:String,
    val token:String,
    val expire:Int,
)
