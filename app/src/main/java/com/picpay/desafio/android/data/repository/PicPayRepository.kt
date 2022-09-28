package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.dao.UserDao
import com.picpay.desafio.android.data.datasources.PicPayService
import com.picpay.desafio.android.data.model.Resource
import com.picpay.desafio.android.data.model.UserDomain
import com.picpay.desafio.android.data.model.UserEntity
import com.picpay.desafio.android.data.model.toDomain
import com.picpay.desafio.android.data.model.toEntity

class PicPayRepository(private val picPayService: PicPayService, private val userDao: UserDao) {

    suspend fun getUsers(): Resource<List<UserDomain>> {
        var cachedUsers = userDao.getAllUsers()

        if (cachedUsers.isEmpty()) {
            try {
                getRemoteUsers()
                cachedUsers = userDao.getAllUsers()
            } catch (e: Exception) {
                return Resource.error(e.message.toString(), null)
            }
        }

        return getLocalUsers(cachedUsers)
    }

    private fun getLocalUsers(cachedUsers: List<UserEntity>): Resource<List<UserDomain>> {
        val users = cachedUsers.map { it.toDomain() }
        return Resource.success(users)
    }

    private suspend fun getRemoteUsers() {
        val users = picPayService.getUsers()
        val mappedUsers = users.map { it.toEntity() }
        userDao.saveUsers(mappedUsers)
    }
}

