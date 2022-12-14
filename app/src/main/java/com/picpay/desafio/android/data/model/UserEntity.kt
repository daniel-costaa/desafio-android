package com.picpay.desafio.android.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "img") val img: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "username") val username: String
)

fun UserEntity.toDomain() =
    UserDomain(img = this.img, name = this.name, id = this.id, username = this.username)
