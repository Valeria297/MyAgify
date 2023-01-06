package com.example.domain.common

sealed class ResultData<T> {
    data class Success<T>(val data: T) : ResultData<T>()
    data class Error<T>(val error: Throwable) : ResultData<T>()
}

inline fun <T, R> ResultData<T>.getResult(
    success: (ResultData.Success<T>) -> R,
    error: (ResultData.Error<T>) -> R
): R = if (this is ResultData.Success) success(this) else error(this as ResultData.Error)

inline fun <T> ResultData<T>.onSuccess(
    block: (T) -> Unit
): ResultData<T> = if (this is ResultData.Success) also { block(data) } else this

inline fun <T> ResultData<T>.onError(
    block: (Throwable) -> Unit
): ResultData<T> = if (this is ResultData.Error) also { block(error) } else this