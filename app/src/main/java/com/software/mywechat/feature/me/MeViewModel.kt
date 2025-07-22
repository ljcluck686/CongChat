package com.software.mywechat.feature.me

import androidx.lifecycle.ViewModel
import com.software.mywechat.MyApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class MeViewModel @Inject constructor(
) :ViewModel(){

    private val _isLogin = MutableStateFlow(false)
    val isLogin : StateFlow<Boolean> = _isLogin


    fun loginOut() {
        MyApplication.instance.logout()
        _isLogin.value = true
    }

}