package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.network.PicPayService

class PicPayRepository(private val picPayService: PicPayService) {
    suspend fun getUsers(): Resource<List<User>> {
        return try {
            val users = picPayService.getUsers()
            Resource.success(users)
        } catch (e: Exception) {
            Resource.error(e.message.toString(), null)
        }
    }
}
