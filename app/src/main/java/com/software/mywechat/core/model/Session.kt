package com.software.mywechat.core.model

import kotlinx.serialization.Serializable


@Serializable
data class Session(
    val token:String,
)
