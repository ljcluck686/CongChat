package com.software.mywechat.core.data.repository

import com.software.app.core.datastore.SessionPreferences
import com.software.app.core.datastore.UserPreferences
import com.software.mywechat.core.datastore.datasource.MyPreferencesDatasource
import com.software.mywechat.core.model.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalUserDataRepository @Inject constructor(
    private val myPreferencesDatasource: MyPreferencesDatasource
) : UserDataRepository {
    override val userData: Flow<UserData> =
        myPreferencesDatasource.userData
    override suspend fun setSession(data: SessionPreferences?) {
        myPreferencesDatasource.setSession(data!!)
    }
    override suspend fun setUser(data: UserPreferences?) {
        myPreferencesDatasource.setUser(data!!)
    }
    override suspend fun logout() {
        myPreferencesDatasource.logout()
    }
}