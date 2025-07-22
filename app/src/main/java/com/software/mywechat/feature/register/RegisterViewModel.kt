package com.software.mywechat.feature.register

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository
):ViewModel(){

    private val _data = MutableStateFlow<User>(User())
    val data : StateFlow<User> = _data

    fun onValueChange(param: User) {
        _data.value = param
    }

    fun onRegisterClick() {
//        Log.d("congcong", "onRegisterClick: ${data.value}")
        val model = data.value
        val param = User(
            nickname = model.nickname,
            phone = model.phone,
            password = model.password,
            avatar = model.avatar,
            sex = model.sex
        )
        viewModelScope.launch {
            userRepository.register(param)
                .asResult()
                .collectLatest {
                    if(it.isSuccess){
                        Log.d("congcong", "accept")
                    }
                }
        }
    }
}
