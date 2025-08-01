package com.software.mywechat.feature.login

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.software.mywechat.MyAppState
import com.software.mywechat.MyApplication
import com.software.mywechat.MyApplication.Companion.TAG
import com.software.mywechat.R
import com.software.mywechat.core.data.repository.UserDataRepository
import com.software.mywechat.core.data.repository.UserRepository
import com.software.mywechat.core.model.User
import com.software.mywechat.core.result.asResult
import com.software.mywechat.util.ImageUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userDataRepository: UserDataRepository,
):ViewModel() {
    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.None)
    val uiState : StateFlow<LoginUiState> = _uiState


    fun onLoginClick(phone:String,password:String){
        if(phone.isBlank()){
            setTipErrorRes(R.string.enter_phone)
            return
        }

        if(TextUtils.isEmpty(password)){
            setTipErrorRes(R.string.enter_password)
            return
        }

        val param = User(
            phone = phone,
            password = password,
        )
        login(param)
    }

    private fun login(param: User) {
        viewModelScope.launch {
            userRepository.login(param)
                .asResult()
                .collectLatest {
                    if(it.isSuccess){
//                        Log.d("congcong", "login: accept")
                        val result = it.getOrThrow()
//                        queryUserInfo(result.data!!.token)
                        val sessionPreference =result.data!!.toPreferences()
                        userDataRepository.setSession(sessionPreference)
                        MyApplication.instance.initAfterLogin(sessionPreference!!)
                        queryUserInfo(result.data!!.token)
                        _uiState.value = LoginUiState.Success
                    }
                }
        }
    }

    private fun queryUserInfo(token: String) {
        viewModelScope.launch {
            userRepository.userInfo(token)
                .asResult()
                .collectLatest {
                    if(it.isSuccess){
                        val result = it.getOrThrow()

                        userDataRepository.setUser(result.data!!.toPreferences())
                        saveImage(result.data!!.info.avatar)
                    }
                }
        }
    }
    private suspend fun saveImage(avatarUrl: String) {
        withContext(Dispatchers.IO) {
            try {
                val fileName = ImageUtils.generateUniqueFileName("avatar")
                val savedPath = ImageUtils.saveImageToFile(MyAppState.applicationContext, avatarUrl, fileName)
                if (!savedPath.isNullOrEmpty()) {
                    userDataRepository.saveAvatarPathToSp(savedPath)
                    MyAppState.localAvatar = savedPath
                } else {
                    Log.e("congcong", "保存头像失败",)
                }
            } catch (e: Exception) {
                Log.e("congcong", "保存头像失败", e)
            }
        }
    }




    private fun setTipErrorRes(res : Int) {
        _uiState.value = LoginUiState.Error(visibility = true, res = res)
    }

}