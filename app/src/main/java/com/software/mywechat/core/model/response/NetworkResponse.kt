package com.software.mywechat.core.model.response

import kotlinx.serialization.Serializable

@Serializable
data class NetworkResponse<T>(
    val code: Int = 0,
    val msg: String,
    val data: T? = null,
) {
    val isSucceeded: Boolean
        get() = code == 200
}