package com.software.mywechat.core.datastore.datasource

import android.util.Log
import androidx.datastore.core.DataStore
import com.software.app.core.datastore.SessionPreferences
import com.software.app.core.datastore.UserDataPreferences
import com.software.app.core.datastore.UserPreferences
import com.software.app.core.datastore.copy
import com.software.mywechat.core.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class MyPreferencesDatasource @Inject constructor (
    private val userPreferences: DataStore<UserDataPreferences>,
){
    val userData = userPreferences.data
        .map {
            UserData(
                session = it.session,
                user = it.user,
                localAvatarPath =it.localAvatarPath,
            )

        }

    suspend fun setSession(data: SessionPreferences) {
        try {
            userPreferences.updateData {
                it.copy { this.session = data }
            }
        } catch (ioException: IOException) {
            Log.e("NiaPreferences", "Failed to update user preferences", ioException)
        }
    }

    suspend fun setUser(data: UserPreferences) {
        try {
            userPreferences.updateData {
                it.copy { this.user = data }
            }
        } catch (ioException: IOException) {
            Log.e("NiaPreferences", "Failed to update user preferences", ioException)
        }
    }

    suspend fun logout() {
        try {
            userPreferences.updateData {
                it.copy {
                    this.session = SessionPreferences.newBuilder().build()
                    this.user = UserPreferences.newBuilder().build()
                    this.localAvatarPath = ""
                }
            }
        } catch (ioException: IOException) {
            Log.e("NiaPreferences", "Failed to update user preferences", ioException)
        }
    }


    suspend fun setLocalAvatarPath(path: String) {
        try {
            userPreferences.updateData { data ->
                data.copy {
                    this.localAvatarPath = path
                }
            }

        } catch (ioException: IOException) {
            Log.e("MyPreferences", "保存头像路径失败", ioException)
        }
    }


    fun getLocalAvatarPath(): Flow<String?> {
        return userPreferences.data.map { data ->
            data.localAvatarPath.takeIf { it.isNotEmpty() }
        }
    }

}