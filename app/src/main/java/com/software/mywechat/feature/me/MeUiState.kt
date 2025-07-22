package com.software.mywechat.feature.me

sealed interface MeUiState {
    data object Success : MeUiState

    data class Error(
        val res: Int? = null,
        val throwable: Throwable? = null,
    ) : MeUiState

    data object Loading : MeUiState
}