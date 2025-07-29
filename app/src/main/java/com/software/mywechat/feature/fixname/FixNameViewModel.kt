package com.software.mywechat.feature.fixname

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.software.mywechat.MyAppState
import com.software.mywechat.core.data.repository.UserDataRepository
import com.software.mywechat.core.data.repository.UserRepository
import com.software.mywechat.core.model.UpdateReq
import com.software.mywechat.core.result.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FixNameViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userDataRepository: UserDataRepository,
) : ViewModel(){
    private val _name = MutableStateFlow<String>(MyAppState.userName)
    val name : StateFlow<String> = _name

    private val _isToProfile = MutableStateFlow<Boolean>(false)
    val isToProfile : StateFlow<Boolean> = _isToProfile

    private val _msg = MutableStateFlow<String>("")
    val msg : StateFlow<String> = _msg

    fun onValueChange(param:String) {
        _name.value = param
    }

    fun onFixName() {

        val updateReq = UpdateReq(

            nickname = name.value,
            avatar = "",
            sex = 0
        )

        viewModelScope.launch {
            userRepository.update(MyAppState.session,updateReq)
                .asResult()
                .collectLatest {
                    if(it.isSuccess){
                        val result = it.getOrThrow()
                        userDataRepository.setUser(result.data!!.toPreferences())
                        _msg.value = "修改成功"
                        _isToProfile.value = true
                    }
                    else{
                        _msg.value = "更新失败"
                    }
                }
        }
    }
}
