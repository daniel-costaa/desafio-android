package com.picpay.desafio.android.data.network

sealed interface Response<out T> {
    data class Success<T>(val data: T) : Response<T>
    data class Error<T>(val code: Int) : Response<T>
}
