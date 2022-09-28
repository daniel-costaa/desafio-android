package com.picpay.desafio.android.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.picpay.desafio.android.data.model.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<UserEntity>
}
