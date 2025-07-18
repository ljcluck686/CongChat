package com.software.mywechat.core.result

import com.software.mywechat.MyApplication
import com.software.mywechat.core.exception.CommonException
import com.software.mywechat.core.model.response.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

fun <T> Flow<T>.asResult(): Flow<Result<T>> = map{
    if (it is NetworkResponse<*>) {
        if (it.isSucceeded) {
            Result.success(it)
        } else {
            Result.failure(CommonException(it))
        }
    } else {
        Result.success(it)
    }
}.onStart {

}.catch {
    emit(Result.failure(it))
}