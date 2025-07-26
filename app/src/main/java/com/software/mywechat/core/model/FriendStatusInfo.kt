package com.software.mywechat.core.model

import kotlinx.serialization.Serializable


@Serializable
data class FriendStatusInfo(
    val IsMuted :Boolean = false, //消息免打扰
    val IsTopped:Boolean = false, //置顶
    val IsBlocked:Boolean = false,//拉黑
    val Remark:String = "", //好友备注
){

}