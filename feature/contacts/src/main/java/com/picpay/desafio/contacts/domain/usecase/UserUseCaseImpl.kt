package com.picpay.desafio.contacts.domain.usecase

import com.picpay.desafio.common.model.Cacheable
import com.picpay.desafio.contacts.domain.model.User
import com.picpay.desafio.contacts.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val emptyName = "Sem nome"
private const val emptyUsername = "Sem username"

internal class UserUseCaseImpl(
    private val userRepository: UserRepository,
) : UserUseCase {
    override suspend fun getUsers(): Flow<Cacheable<List<User>>> {
        return userRepository.getUsers().map { result ->
            result.copy(
                data = result.data.map { user ->
                    user.copy(
                        name = user.name.ifBlank { emptyName },
                        username = user.username.ifBlank { emptyUsername }
                    )
                }
            )
        }
    }
}