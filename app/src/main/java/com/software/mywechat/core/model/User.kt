package com.software.mywechat.core.model


import com.software.app.core.datastore.UserPreferences
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable



@Serializable
data class User(
    val id: String = "",
    val phone:String ="",
    val nickname:String="",
    val password:String="",
    val avatar:String="https://chan-xin.oss-cn-beijing.aliyuncs.com/chan_xin/image/1752555249209.jpg",
    val sex:Byte=0,
){

}
