package com.software.mywechat.feature.me

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.software.mywechat.MyAppState
import com.software.mywechat.MyApplication
import com.software.mywechat.core.data.repository.UserDataRepository
import com.software.mywechat.core.data.repository.UserRepository
import com.software.mywechat.core.model.Session
import com.software.mywechat.core.model.User
import com.software.mywechat.core.model.UserInfo
import com.software.mywechat.core.model.response.NetworkResponse
import com.software.mywechat.core.result.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.Thread.State
import javax.inject.Inject


@HiltViewModel
class MeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userDataRepository: UserDataRepository,
) :ViewModel(){

    private val _isLogin = MutableStateFlow(false)
    val isLogin : StateFlow<Boolean> = _isLogin

    private val _uiState = MutableStateFlow<MeUiState>(MeUiState.Loading)
    val uiState : StateFlow<MeUiState> = _uiState

    private val _data = MutableStateFlow<UserInfo>(UserInfo())
    val data : StateFlow<UserInfo> = _data

    init {
        loadData()
        Log.d("congcong", "${_data.value.nickname} ")
    }

    private fun loadData() {
        viewModelScope.launch {
            userRepository.userInfo(MyAppState.session)
                .asResult()
                .collectLatest {
                    if(it.isSuccess){
                        val res =it.getOrThrow().data?.info!!
                        _data.emit(res)
                        _uiState.emit(MeUiState.Success)
                    }else{
                        _uiState.emit(MeUiState.Error(throwable = it.exceptionOrNull()))
                    }
                }
        }
    }

    fun loginOut() {
        MyApplication.instance.logout()
        _isLogin.value = true
    }

}