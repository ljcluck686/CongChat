package com.software.mywechat.feature.newfriend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.software.mywechat.MyAppState
import com.software.mywechat.core.data.repository.FriendRepository
import com.software.mywechat.core.data.repository.UserRepository
import com.software.mywechat.core.database.model.FriendEntity
import com.software.mywechat.core.model.DataListWrapper
import com.software.mywechat.core.model.FriendApplyAction
import com.software.mywechat.core.model.FriendApplyResp
import com.software.mywechat.core.network.retrofit.MyNetworkApiService
import com.software.mywechat.core.result.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class NewFriendViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val friendRepository: FriendRepository,
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
                        val res = it.getOrNull()?.data!!
                        _datum.emit(res)
                        for( i in res.list){
                            val ret = FriendEntity(
                                userId = i.userId,
                                nickname = i.nickname,
                                avatarUrl = i.avatarUrl,
                                gender = i.gender,
                                greetMsg = i.greetMsg,
                                status = i.status,
                                permissions = 0,
                            )
                            friendRepository.insert(ret)
                        }
                    }
                    else{
                        val friendEntities = friendRepository.getAll().first()
                        val friendApplyList = friendEntities.map {
                            FriendApplyResp(
                                userId = it.userId,
                                nickname = it.nickname,
                                avatarUrl = it.avatarUrl,
                                gender = it.gender,
                                greetMsg = it.greetMsg,
                                status = it.status
                            )
                        }
                        _datum.emit(DataListWrapper(friendApplyList))
                    }
                }

        }

    }

    fun toConfirm(param: String) {
        viewModelScope.launch {
            val ret = FriendApplyAction(
                applicantId = param,
                targetId = MyAppState.userId,
                isApproved = true
            )
            userRepository.handleFriendApply(ret)
                .asResult()
                .collectLatest {
                    if(it.isSuccess){
                        withContext(Dispatchers.IO) {
                            val user = friendRepository.getFriend(param)
                            val updatedUser = user.copy(status = 1)
                            friendRepository.update(updatedUser)
                        }
                    }
                }
        }
        loadData()
    }

    fun toRefuse(param: String) {
        viewModelScope.launch {
            val ret = FriendApplyAction(
                applicantId = param,
                targetId = MyAppState.userId,
                isApproved = false
            )
            userRepository.handleFriendApply(ret)
                .asResult()
                .collectLatest {
                    if(it.isSuccess){
                        withContext(Dispatchers.IO) {
                            val user = friendRepository.getFriend(param)
                            val updatedUser = user.copy(status = 2)
                            friendRepository.update(updatedUser)
                        }
                    }
                }
        }
        loadData()
    }


}