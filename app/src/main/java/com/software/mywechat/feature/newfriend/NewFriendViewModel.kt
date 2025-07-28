package com.software.mywechat.feature.newfriend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.software.mywechat.MyAppState
import com.software.mywechat.core.data.repository.UserRepository
import com.software.mywechat.core.model.DataListWrapper
import com.software.mywechat.core.model.FriendApplyResp
import com.software.mywechat.core.network.retrofit.MyNetworkApiService
import com.software.mywechat.core.result.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewFriendViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel(){

    private val _datum = MutableStateFlow<DataListWrapper>(DataListWrapper(emptyList()))
    val datum : StateFlow<DataListWrapper> = _datum

    init {
        loadData()
    }

    private fun loadData() {
//        viewModelScope.launch {
//            userRepository.getFriendApplyList(MyAppState.userId)
//                .asResult()
//                .collectLatest {
//                    if (it.isSuccess) {
//                        _datum.emit(it.getOrNull()?.data!!)
//                    }
//                }
//        }
        viewModelScope.launch {
            userRepository.getHandleFriendApplyList(MyAppState.userId)
                .asResult()
                .collectLatest {
                    if(it.isSuccess){
                        _datum.emit(it.getOrNull()?.data!!)
                    }
                }
        }

    }


}