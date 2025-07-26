package com.software.mywechat.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.software.mywechat.core.data.repository.UserRepository
import com.software.mywechat.core.model.Infos
import com.software.mywechat.core.model.User
import com.software.mywechat.core.result.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val userRepository: UserRepository
) :ViewModel(){
    private val _query = MutableStateFlow<String>("")
    val query:StateFlow<String> = _query

    private val _placeholder = MutableStateFlow<String>("搜索 账号/手机号")
    val placeholder: StateFlow<String> = _placeholder

    private val _datum = MutableStateFlow<Infos>(Infos(emptyList()))
    val datum : StateFlow<Infos> = _datum

    fun onQueryChange(data: String) {
        _query.value = data
    }
    fun onSearchClick() {
        if (query.value.isBlank()) {
            _query.value = placeholder.value
        }
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            userRepository.findUser(name = _query.value, phone = _query.value)
                .asResult()
                .collectLatest {
                    if(it.isSuccess){
                        _datum.emit(it.getOrNull()?.data!!)
                    }
                }
        }
    }

}
