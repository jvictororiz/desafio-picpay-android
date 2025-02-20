package com.picpay.desafio.contacts.data.repository


import app.cash.turbine.test
import com.picpay.desafio.contacts.data.datasource.local.UserLocalDataSource
import com.picpay.desafio.contacts.data.datasource.remote.UserRemoteDataSource
import com.picpay.desafio.contacts.data.model.UserRemote
import com.picpay.desafio.contacts.domain.repository.UserRepository
import com.picpay.desafio.persistence.entity.UserLocal
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class UserRepositoryTest {
    private val localDataSource: UserLocalDataSource = mockk(relaxed = true)
    private val remoteDataSource: UserRemoteDataSource = mockk(relaxed = true)

    private val userRepository: UserRepository =
        UserRepositoryImpl(remoteDataSource, localDataSource)

    @Test
    fun `when there is no cache, only returns remote data`() = runTest {
        val expectedCacheUsers = listOf<UserLocal>()
        val expectedRemoteUsers = listOf(UserRemote("image", "sem cache", "123", "@joao"))

        coEvery { localDataSource.getUsers() } returns expectedCacheUsers
        coEvery { remoteDataSource.getUsers() } returns expectedRemoteUsers

        userRepository.getUsers().test {
            val result = awaitItem()
            assertEquals(result.cache, false)
            assertEquals(result.data.first().name, "sem cache")
            awaitComplete()
        }
        coVerify(exactly = 1) { localDataSource.deleteAll() }
        coVerify(exactly = 1) { localDataSource.saveUsers(any()) }
    }

    @Test
    fun `when it has cache, it returns the cache and then remote data`() = runTest {
        val expectedCacheUsers = listOf(UserLocal("123", "image", "com cache", "@joao"))
        val expectedRemoteUsers = listOf(UserRemote("image", "sem cache", "123", "@joao"))

        coEvery { localDataSource.getUsers() } returns expectedCacheUsers
        coEvery { remoteDataSource.getUsers() } returns expectedRemoteUsers

        userRepository.getUsers().test {
            val first = awaitItem()
            assertEquals(first.cache, true)
            assertEquals(first.data.first().name, "com cache")

            val second = awaitItem()
            assertEquals(second.cache, false)
            assertEquals(second.data.first().name, "sem cache")

            awaitComplete()
        }
        coVerify(exactly = 1) { localDataSource.deleteAll() }
        coVerify(exactly = 1) { localDataSource.saveUsers(any()) }
    }

}