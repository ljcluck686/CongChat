package com.software.mywechat.feature.wechat

import androidx.lifecycle.ViewModel
import com.software.mywechat.core.data.repository.WebSocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class WeChatViewModel @Inject constructor(
    private val webSocketRepository: WebSocketRepository
):ViewModel() {



}