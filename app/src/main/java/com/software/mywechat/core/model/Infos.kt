package com.software.mywechat.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Infos (
    val infos : List<User>
){
}