package com.picpay.desafio.android.data

import androidx.annotation.NonNull
import androidx.annotation.Nullable

class Resource<T> private constructor(val status: Status, val data: T?) {

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {

        fun <T> success(@NonNull data: T): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
            )
        }

        fun <T> error(msg: String, @Nullable data: T?): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
            )
        }

        fun <T> loading(@Nullable data: T? = null): Resource<T> {
            return Resource(
                Status.LOADING,
                data
            )
        }
    }
}
