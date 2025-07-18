package com.software.mywechat.core.model.response

import kotlinx.serialization.Serializable

@Serializable
data class NetworkResponse<T>(
    val data: T? = null,
    val status: Int = 0,
) {
    val isSucceeded: Boolean
        get() = status == 200
}