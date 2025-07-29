package com.software.mywechat

import android.app.Application
import android.util.Log
import com.software.mywechat.core.data.repository.UserDataRepository
import com.software.mywechat.util.ImageUtils
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltAndroidApp
class MyApplication: Application() {

    @Inject
    lateinit var userDataRepository: UserDataRepository

    private val applicationScope = CoroutineScope(SupervisorJob())


    override fun onCreate() {
        super.onCreate()
        instance = this
        MyAppState.applicationContext = this
        applicationScope.launch {
            userDataRepository.userData
                .distinctUntilChanged()
                .collectLatest { userData ->
                    MyAppState.session = userData.session.token
                    MyAppState.userId = userData.session.id
                    MyAppState.userName = userData.user.nickname
                    MyAppState.phone = userData.user.phone
//                    saveProfileImage(userData.user.avatar)
                    Log.d(TAG, "saveProfileImage: ${MyAppState.localAvatar }")
//                    ImageUtils.saveImageToFile(this@MyApplication,userData.user.avatar)
                }

        }
    }

//    private suspend fun saveProfileImage(imageUrl: String) {
//        withContext(Dispatchers.IO) {
//            try {
//                Log.d(TAG, "saveProfileImage: ${MyAppState.localAvatar }")
//                if (!ImageUtils.hasLocalImage(this@MyApplication)) {
//                    val localPath = ImageUtils.saveImageToFile(this@MyApplication, imageUrl)
//                    if (localPath != null) {
//                        MyAppState.localAvatar = localPath
//                        Log.d("LoginViewModel", "第一次保存成功，localAvatar 设置为: $localPath")
//                    }
//                    else{
//
//                        Log.d("LoginViewModel", "第二次保存失败，localAvatar 设置为: $localPath")
//                    }
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }

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