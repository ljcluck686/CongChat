package com.software.mywechat.core.model


import com.software.mywechat.core.database.model.FriendEntity
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val id: String = "",
    val phone:String ="",
    val nickname:String ="",
    val sex:Byte=0,
    var avatar:String="https://chan-xin.oss-cn-beijing.aliyuncs.com/chan_xin/image/1752318169872",
    val state:FriendStatusInfo = FriendStatusInfo()
){
    fun toFriendEntity(): FriendEntity {
        return FriendEntity(
            userId = id,
            nickname = nickname,
            avatarUrl = avatar,
            gender = sex.toInt(),
            greetMsg = "",
            status = 1,
            permissions = 0
        )
    }
}