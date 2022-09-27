package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.model.Resource
import com.picpay.desafio.android.data.model.UserDto
import com.picpay.desafio.android.data.network.PicPayService

class PicPayRepository(private val picPayService: PicPayService) {
    suspend fun getUsers(): Resource<List<UserDto>> {
        return try {
            val users = picPayService.getUsers()
            Resource.success(users)
        } catch (e: Exception) {
            Resource.error(e.message.toString(), null)
        }
    }
}
