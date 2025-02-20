package com.picpay.desafio.contacts.data.datasource


import com.picpay.desafio.contacts.data.datasource.local.UserLocalDataSource
import com.picpay.desafio.contacts.data.datasource.local.UserLocalDataSourceImpl
import com.picpay.desafio.persistence.room.dao.UserDao
import com.picpay.desafio.persistence.room.entity.UserLocal
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UserLocalDataSourceTest {
    private val userDao: UserDao = mockk(relaxed = true)

    private val userLocalDataSource: UserLocalDataSource =
        UserLocalDataSourceImpl(userDao = userDao)

    @Test
    fun `when calling getUsers then call getUsers from dao`() = runTest {
        userLocalDataSource.getUsers()

        coVerify(exactly = 1) { userDao.getUsers() }
    }

    @Test
    fun `when calling saveUsers then call insertAll from dao`() = runTest {
        val expectedCacheUsers = listOf(UserLocal("", "", "", ""))
        userLocalDataSource.saveUsers(expectedCacheUsers)
        coVerify(exactly = 1) { userDao.insertAll(expectedCacheUsers) }
    }

    @Test
    fun `when calling deleteAll then call deleteAll from dao`() = runTest {
        userLocalDataSource.deleteAll()
        coVerify(exactly = 1) { userDao.deleteAll() }
    }

}