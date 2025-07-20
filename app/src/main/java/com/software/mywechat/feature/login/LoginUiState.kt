package com.software.mywechat.feature.login

sealed interface LoginUiState {
    data object Success : LoginUiState

    data class Error(
        val visibility: Boolean = false,
        val res: Int? = null,
        val throwable: Throwable? = null,
    ) : LoginUiState

    data object Loading : LoginUiState
    data object None : LoginUiState
}