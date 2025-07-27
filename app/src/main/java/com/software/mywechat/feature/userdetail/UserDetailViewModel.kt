package com.software.mywechat.feature.userdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
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
class UserDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userRepository: UserRepository,
):ViewModel(){

    private val userId : String = checkNotNull(savedStateHandle[USER_ID])

    private val _data = MutableStateFlow<User>(User())
    val data : StateFlow<User> = _data

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            userRepository.findUser(name = userId, phone = userId, ids = userId)
                .asResult()
                .collectLatest {
                    if(it.isSuccess){
                        _data.emit(it.getOrThrow().data!!.infos[0])
                    }
                }
        }
    }

}