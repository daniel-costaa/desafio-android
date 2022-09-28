package com.picpay.desafio.android.data.datasources

import androidx.room.Database
import com.picpay.desafio.android.data.dao.UserDao
import com.picpay.desafio.android.data.model.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class PicPayDatabase {
    abstract fun userDao(): UserDao
}
