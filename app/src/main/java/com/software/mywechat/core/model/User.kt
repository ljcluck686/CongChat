package com.software.mywechat.core.model


import com.software.app.core.datastore.UserPreferences
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable



@Serializable
data class User(
    val id: String? = null,
    val phone:String,
    val nickname:String?="",
    val password:String,
    var avatar:String?="https://chan-xin.oss-cn-beijing.aliyuncs.com/chan_xin/image/1752318169872",
    val sex:Byte?=0,
){
    fun toPreferences(): UserPreferences {
        return UserPreferences.newBuilder()
            .setId(id)
            .setPhone(phone)
            .setNickname(nickname)
            .setAvatar(avatar)
            .build()
    }
}
