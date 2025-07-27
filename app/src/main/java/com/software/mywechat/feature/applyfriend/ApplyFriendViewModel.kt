package com.software.mywechat.feature.applyfriend

import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.software.mywechat.MyAppState
import com.software.mywechat.core.data.repository.UserRepository
import com.software.mywechat.core.model.FriendApplyRequest
import com.software.mywechat.core.result.asResult
import com.software.mywechat.feature.userdetail.USER_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ApplyFriendViewModel @Inject  constructor(
    savedStateHandle: SavedStateHandle,
    private val userRepository: UserRepository,
) : ViewModel(){
    private val userId:String = checkNotNull(savedStateHandle[USER_ID])

    private val _tx = MutableStateFlow<String>("")
    val tx : StateFlow<String> = _tx

    init{
        loadData()

    }

    private fun loadData() {
        viewModelScope.launch {
            userRepository.userInfo(MyAppState.session)
                .asResult()
                .collectLatest {
                    if(it.isSuccess){
                        val data = it.getOrThrow().data!!.info
                        _tx.value = data.nickname
                    }
                }
        }
    }

    fun onApplyFriend(msg:String) {
        viewModelScope.launch {

            userRepository.applyFriend(FriendApplyRequest(
                applicantId = MyAppState.userId, targetId = userId, greetMsg = msg
            ))
                .asResult()
                .collectLatest {
                    if(it.isSuccess){
                    }
                }
        }
    }

}