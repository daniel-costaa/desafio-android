package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.model.Resource
import com.picpay.desafio.android.data.model.UserDomain
import com.picpay.desafio.android.data.model.toDomain
import com.picpay.desafio.android.data.network.PicPayService

class PicPayRepository(private val picPayService: PicPayService) {
    suspend fun getUsers(): Resource<List<UserDomain>> {
        return try {
            val users = picPayService.getUsers()
            Resource.success(users.map { it.toDomain() })
        } catch (e: Exception) {
            Resource.error(e.message.toString(), null)
        }
    }
}
