package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.network.PicPayService

class PicPayRepository(private val picPayService: PicPayService) {
    suspend fun getUsers() = picPayService.getUsers()
}
