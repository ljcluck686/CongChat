package com.software.mywechat.feature.addressbook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.software.mywechat.MyAppState
import com.software.mywechat.core.data.repository.MyFriendRepository
import com.software.mywechat.core.data.repository.UserRepository
import com.software.mywechat.core.database.model.FriendEntity
import com.software.mywechat.core.database.model.MyFriendEntity
import com.software.mywechat.core.model.DataListWrapper
import com.software.mywechat.core.model.FriendApplyResp
import com.software.mywechat.core.model.FriendList
import com.software.mywechat.core.model.User
import com.software.mywechat.core.model.UserInfo
import com.software.mywechat.core.result.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddresBookViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val myFriendRepository: MyFriendRepository,
):ViewModel(){
    private val _datum = MutableStateFlow<FriendList>(FriendList(emptyList()))
    val datum : StateFlow<FriendList> = _datum

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            userRepository.getFriendList(MyAppState.userId)
                .asResult()
                .collectLatest {
                    if(it.isSuccess){
                        val res =it.getOrThrow().data!!
                        _datum.emit(res)
                        updateDao()
                    }
                    else{
                        val friendEntities = myFriendRepository.getAll().first()
                        val ret: List<User> = friendEntities.map {
                            User(
                                id = it.userId,
                                nickname = it.nickname,
                                avatar = it.avatarUrl,
                                sex = it.gender.toByte()
                            )
                        }
                        _datum.emit(
                            FriendList(
                                friendList =ret
                            )
                        )
                    }
                }
        }
    }

    private fun updateDao() {
        viewModelScope.launch {
            userRepository.getHandleFriendApplyList(MyAppState.userId)
                .asResult()
                .collectLatest {
                    if (it.isSuccess) {
                        val res = it.getOrNull()?.data!!.list
                        for (i in res) {
                            if(i.status==1){
                                val ret = MyFriendEntity(
                                    userId = i.userId,
                                    nickname = i.nickname,
                                    avatarUrl = i.avatarUrl,
                                    gender = i.gender,
                                    permissions = 0,
                                )
                                myFriendRepository.insert(ret)
                            }

                        }
                    }
                }
        }
    }

}