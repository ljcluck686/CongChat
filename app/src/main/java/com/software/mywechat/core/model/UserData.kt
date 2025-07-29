package com.software.mywechat.core.model

import com.software.app.core.datastore.SessionPreferences
import com.software.app.core.datastore.UserPreferences
import java.io.File

data class UserData(
    val session: SessionPreferences = SessionPreferences.newBuilder().build(),
    val user: UserPreferences = UserPreferences.newBuilder().build(),
    val localAvatarPath: String? = null
){
    fun isLogin(): Boolean {
        return session.id.isNotBlank()
    }

    fun hasLocalAvatar(): Boolean {
        return localAvatarPath != null && File(localAvatarPath).exists()
    }

}