package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.dao.UserDao
import com.picpay.desafio.android.data.datasources.PicPayService
import com.picpay.desafio.android.data.model.Resource
import com.picpay.desafio.android.data.model.UserDto
import com.picpay.desafio.android.data.repository.PicPayRepository
import io.mockk.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PicPayRepositoryTest {
    private val picPayService = mockk<PicPayService>()
    private val userDao = mockk<UserDao>()

    private val picPayRepository = PicPayRepository(picPayService, userDao)

    @Test
    fun givenNoCacheThenShouldMakeApiCall() {
        // Mock
        val dtoUsers = listOf(UserDto("", "", 1, ""))

        coEvery { userDao.getAllUsers() } returns emptyList()
        coJustRun { userDao.saveUsers(any()) }
        coEvery { picPayService.getUsers() } returns dtoUsers

        // Run
        val result = runBlocking {
            picPayRepository.getUsers()
        }
        // Assert
        assertEquals(Resource.Status.SUCCESS, result.status)
        coVerify { picPayService.getUsers() }
    }
}
