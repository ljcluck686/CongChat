package com.software.mywechat.feature.login

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.software.mywechat.R
import com.software.mywechat.core.data.repository.UserRepository
import com.software.mywechat.core.model.User
import com.software.mywechat.core.result.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
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
                        Log.d("congcong", "login: accept")
                    }
                }
        }
    }

    private fun setTipErrorRes(res : Int) {
        _uiState.value = LoginUiState.Error(visibility = true, res = res)
    }


}