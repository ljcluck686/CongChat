package com.software.mywechat.feature.guide

import android.os.CountDownTimer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.software.mywechat.core.data.repository.UserDataRepository
import com.software.mywechat.ui.MyAppUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class GuideViewModel @Inject constructor(
) :ViewModel() {
    private var timer :CountDownTimer?=null
    private val _timeLeft = MutableStateFlow(0L)
    val timeLeft: StateFlow<Long> = _timeLeft
    private val _navigateToNext = MutableStateFlow(false)
    val navigateToNext:StateFlow<Boolean> = _navigateToNext



    init {
        delayToNext()
    }

    private fun delayToNext(time :Long = 2000) {
        timer = object : CountDownTimer(2000, 1000) {
            override fun onFinish() {
                _navigateToNext.value = true
            }

            override fun onTick(millisUntilFinished: Long) {
                _timeLeft.value = millisUntilFinished / 1000+1
            }
        }.start()
    }
}