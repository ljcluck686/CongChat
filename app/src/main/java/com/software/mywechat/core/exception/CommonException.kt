package com.software.mywechat.core.exception

import com.software.mywechat.core.model.response.NetworkResponse

class CommonException(
    /**
     * 网络响应
     */
    val networkResponse: NetworkResponse<*>? = null,

    val throwable: Throwable? = null,
) : RuntimeException()