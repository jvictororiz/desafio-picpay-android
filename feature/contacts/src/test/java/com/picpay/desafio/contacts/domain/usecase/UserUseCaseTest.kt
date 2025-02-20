package com.picpay.desafio.contacts.domain.usecase

import app.cash.turbine.test
import com.picpay.desafio.common.model.Cacheable
import com.picpay.desafio.contacts.domain.model.User
import com.picpay.desafio.contacts.domain.repository.UserRepository
import com.picpay.desafio.contacts.domain.usecase.UserUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Date

class UserUseCaseTest {

    private val userRepository: UserRepository = mockk(relaxed = true)

    private var useCase: UserUseCaseImpl = UserUseCaseImpl(userRepository = userRepository)

    @Test
    fun `when the user has a name and username, then keep the names`() = runTest {
        val expectedUser = User("", "joao", "", "@joao")
        coEvery { userRepository.getUsers() } returns flowOf(
            Cacheable(
                cache = false,
                lastModification = Date(),
                listOf(expectedUser)
            )
        )

        useCase.getUsers().test {
            val userResult = awaitItem().data.first()
            assertEquals(userResult.name, "joao")
            assertEquals(userResult.username, "@joao")
            awaitComplete()
        }
    }

    @Test
    fun `when the user has no name or username, then keep the names`() = runTest {
        val expectedUser = User("", "", "", "")
        coEvery { userRepository.getUsers() } returns flowOf(
            Cacheable(
                cache = false,
                lastModification = Date(),
                listOf(expectedUser)
            )
        )

        useCase.getUsers().test {
            val userResult = awaitItem().data.first()
            assertEquals(userResult.name, "Sem nome")
            assertEquals(userResult.username, "Sem username")
            awaitComplete()
        }
    }
}