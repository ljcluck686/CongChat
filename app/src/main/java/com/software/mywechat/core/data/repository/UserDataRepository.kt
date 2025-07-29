package com.software.mywechat.core.data.repository

import com.software.app.core.datastore.SessionPreferences
import com.software.app.core.datastore.UserPreferences
import com.software.mywechat.core.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    val userData: Flow<UserData>
    suspend fun setSession(data: SessionPreferences?)
    suspend fun setUser(data: UserPreferences?)
    suspend fun logout()
    suspend fun saveAvatarPathToSp(path: String)
    fun getLocalAvatarPath(): Flow<String?>
}