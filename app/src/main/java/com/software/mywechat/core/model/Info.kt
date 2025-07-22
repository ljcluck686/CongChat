package com.software.mywechat.core.model

import com.software.app.core.datastore.UserPreferences
import kotlinx.serialization.Serializable

@Serializable
data class Info(
    val info : UserInfo
){
    fun toPreferences(): UserPreferences {
        return UserPreferences.newBuilder()
            .setId(info.id)
            .setPhone(info.phone)
            .setNickname(info.nickname)
            .setAvatar(info.avatar)
            .build()
    }
}
