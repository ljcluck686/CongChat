package com.software.mywechat

import android.app.Application
import com.software.mywechat.core.data.repository.UserDataRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MyApplication: Application() {

    @Inject
    lateinit var userDataRepository: UserDataRepository

    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        instance = this
//        applicationScope.launch {
//            userDataRepository.userData
//                .map{ it.session }
//                .distinctUntilChanged()
//                .collectLatest {
//                    MyAppState.session = it.token
//                    MyAppState.userId = it.id
//
//                }
//        }
//        applicationScope.launch {
//            userDataRepository.userData
//                .map { it.user }
//                .distinctUntilChanged()
//                .collectLatest {
//                    MyAppState.userName = it.nickname
//                    MyAppState.phone = it.phone
//                }
//        }
        applicationScope.launch {
            userDataRepository.userData
                .distinctUntilChanged()
                .collectLatest { userData ->
                    MyAppState.session = userData.session.token
                    MyAppState.userId = userData.session.id
                    MyAppState.userName = userData.user.nickname
                    MyAppState.phone = userData.user.phone

                }
        }
    }
    fun logout() {
        logoutSilence()
    }

    private fun logoutSilence() {
        applicationScope.launch {
            userDataRepository.logout()
        }
    }

    companion object {
        const val TAG = "congcong"
        lateinit var instance: MyApplication
    }
}