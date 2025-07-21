package com.software.mywechat.core.model

import com.software.app.core.datastore.SessionPreferences
import com.software.app.core.datastore.UserPreferences

data class UserData(
    val session: SessionPreferences = SessionPreferences.newBuilder().build(),
    val user: UserPreferences = UserPreferences.newBuilder().build(),
){
    fun isLogin(): Boolean {
        return session.id.isNotBlank()
    }
}