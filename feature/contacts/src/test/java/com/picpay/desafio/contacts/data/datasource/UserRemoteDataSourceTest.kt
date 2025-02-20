package com.picpay.desafio.contacts.data.datasource


import com.picpay.desafio.common.exception.DefaultException
import com.picpay.desafio.common.exception.NetworkConnectionException
import com.picpay.desafio.contacts.data.datasource.remote.UserRemoteDataSource
import com.picpay.desafio.contacts.data.datasource.remote.UserRemoteDataSourceImpl
import com.picpay.desafio.contacts.data.model.UserRemote
import com.picpay.desafio.contacts.data.api.UserApi
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class UserRemoteDataSourceTest {
    private val userApi: UserApi = mockk(relaxed = true)

    private val userRemoteDataSource: UserRemoteDataSource =
        UserRemoteDataSourceImpl(userApi = userApi)

    @Test
    fun `when calling getUsers without network, then throw NetworkConnectionException`() =
        runTest {
            coEvery { userApi.getUsers() } throws IOException()

            assertFailsWith<NetworkConnectionException> {
                userRemoteDataSource.getUsers()
            }
        }

    @Test
    fun `when calling getUsers with other error, then throw DefaultException`() = runTest {
        coEvery { userApi.getUsers() } throws Exception()

        assertFailsWith<DefaultException> {
            userRemoteDataSource.getUsers()
        }
    }

    @Test
    fun `when calling getUsers with success, then returns users`() = runTest {
       val expectedUsers = listOf(UserRemote("","","",""))

        coEvery { userApi.getUsers() } returns expectedUsers

        val result = userApi.getUsers()

        assertEquals(result, expectedUsers)
    }

}