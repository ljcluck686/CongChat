package com.software.mywechat.feature.addressbook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.software.mywechat.MyAppState
import com.software.mywechat.core.data.repository.UserRepository
import com.software.mywechat.core.model.UserInfo
import com.software.mywechat.core.result.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddresBookViewModel @Inject constructor(
    private val userRepository: UserRepository
):ViewModel(){
    private val _datum = MutableStateFlow<UserInfo>(UserInfo())
    val datum : StateFlow<UserInfo> = _datum

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            userRepository.getFriendList(MyAppState.userId)
                .asResult()
                .collectLatest {
                    if(it.isSuccess){
                        val res =it.getOrThrow().data?.info!!
                        _datum.emit(res)
                    }
                }
        }
    }

}