package com.software.mywechat.core.model

import com.software.app.core.datastore.SessionPreferences
import kotlinx.serialization.Serializable


@Serializable
data class Session(
    val id:String,
    val token:String,
    val expire:Int,
){
    fun toPreferences(): SessionPreferences? {
        return SessionPreferences.newBuilder()
            .setId(id)
            .setToken(token)
            .setExpire(expire.toString())
            .build()
    }
}
