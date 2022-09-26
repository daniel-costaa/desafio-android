package com.picpay.desafio.android.data.network

import com.picpay.desafio.android.data.User
import retrofit2.Call
import retrofit2.http.GET

interface PicPayService {
    @GET("users")
    suspend fun getUsers(): Call<List<User>>
}
