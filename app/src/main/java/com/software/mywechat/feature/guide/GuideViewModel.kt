package com.software.mywechat.feature.guide

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GuideViewModel :ViewModel() {
    private var timer :CountDownTimer?=null
    private val _timeLeft = MutableStateFlow(0L)
    val timeLeft: StateFlow<Long> = _timeLeft
    private val _navigateToMain = MutableStateFlow(false)
    val navigateToMain:StateFlow<Boolean> = _navigateToMain

    init {
        delayToNext()
    }

    private fun delayToNext(time :Long = 2000) {
        timer = object : CountDownTimer(2000, 1000) {
            override fun onFinish() {
                _navigateToMain.value = true
            }

            override fun onTick(millisUntilFinished: Long) {
                _timeLeft.value = millisUntilFinished / 1000+1
            }
        }.start()
    }
}