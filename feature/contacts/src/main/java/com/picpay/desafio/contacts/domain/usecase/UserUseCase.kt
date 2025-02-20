package com.picpay.desafio.contacts.domain.usecase

import com.picpay.desafio.common.model.Cacheable
import com.picpay.desafio.contacts.domain.model.User
import kotlinx.coroutines.flow.Flow

internal interface UserUseCase {

    suspend fun getUsers(): Flow<Cacheable<List<User>>>
}