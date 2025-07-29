package com.software.mywechat.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.software.mywechat.MyAppState
import com.software.mywechat.core.data.repository.UserDataRepository
import com.software.mywechat.core.data.repository.UserRepository
import com.software.mywechat.core.model.User
import com.software.mywechat.core.model.UserInfo
import com.software.mywechat.core.result.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
):ViewModel(){
    private val _data = MutableStateFlow<UserInfo>(UserInfo())
    val data:StateFlow<UserInfo> = _data

    val avatarPathFlow: Flow<String?> = userDataRepository.getLocalAvatarPath()

//    init{
//        loadData()
//    }

//    private fun loadData() {
//        viewModelScope.launch {
//            userRepository.userInfo(MyAppState.session)
//                .asResult()
//                .collectLatest {
//                    if(it.isSuccess){
//                        _data.emit(it.getOrThrow().data?.info!!)
//                    }
//                }
//        }
//    }

}