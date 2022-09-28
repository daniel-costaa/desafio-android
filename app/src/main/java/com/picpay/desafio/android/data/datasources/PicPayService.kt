package com.picpay.desafio.android.data.datasources

import com.picpay.desafio.android.data.model.UserDto
import retrofit2.http.GET

interface PicPayService {
    @GET("users")
    suspend fun getUsers(): List<UserDto>
}
