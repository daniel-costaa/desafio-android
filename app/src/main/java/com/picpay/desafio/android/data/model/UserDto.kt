package com.picpay.desafio.android.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDto(
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String
) : Parcelable


fun UserDto.toDomain() =
    UserDomain(img = this.img, name = this.name, id = this.id, username = this.username)
