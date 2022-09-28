package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.dao.UserDao
import com.picpay.desafio.android.data.datasources.PicPayService
import com.picpay.desafio.android.data.model.*

class PicPayRepository(private val picPayService: PicPayService, private val userDao: UserDao) {

    suspend fun getUsers(): Resource<List<UserDomain>> {
        var cachedUsers = userDao.getAllUsers()

        if (cachedUsers.isEmpty()) {
            try {
                saveRemoteUsers(getRemoteUsers())
                cachedUsers = userDao.getAllUsers()
            } catch (e: Exception) {
                return Resource.error(e.message.toString(), null)
            }
        }

        return mapCachedUsersToDomain(cachedUsers)
    }

    private suspend fun getRemoteUsers() = picPayService.getUsers()

    private suspend fun saveRemoteUsers(remoteUsers: List<UserDto>) {
        val mappedUsers = remoteUsers.map { it.toEntity() }
        userDao.saveUsers(mappedUsers)
    }

    private fun mapCachedUsersToDomain(cachedUsers: List<UserEntity>): Resource<List<UserDomain>> {
        val users = cachedUsers.map { it.toDomain() }
        return Resource.success(users)
    }
}

