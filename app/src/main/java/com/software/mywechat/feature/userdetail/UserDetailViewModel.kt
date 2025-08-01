package com.software.mywechat.feature.userdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.software.mywechat.core.data.repository.FriendRepository
import com.software.mywechat.core.data.repository.MyFriendRepository
import com.software.mywechat.core.data.repository.UserRepository
import com.software.mywechat.core.database.model.FriendEntity
import com.software.mywechat.core.model.User
import com.software.mywechat.core.model.UserInfo
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
    private val friendRepository: FriendRepository,
):ViewModel(){

    private val userId : String = checkNotNull(savedStateHandle[USER_ID])

    private val _data = MutableStateFlow<FriendEntity>(FriendEntity())
    val data : StateFlow<FriendEntity> = _data

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val res = friendRepository.getFriend(userId)
            _data.emit(res)
        }
    }

}