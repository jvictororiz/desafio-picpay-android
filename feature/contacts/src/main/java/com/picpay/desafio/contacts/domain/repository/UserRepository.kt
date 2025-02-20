package com.picpay.desafio.contacts.domain.repository

import com.picpay.desafio.common.model.Cacheable
import com.picpay.desafio.contacts.domain.model.User
import kotlinx.coroutines.flow.Flow

internal interface UserRepository {

    suspend fun getUsers(): Flow<Cacheable<List<User>>>
}