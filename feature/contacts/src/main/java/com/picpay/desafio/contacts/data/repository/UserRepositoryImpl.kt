package com.picpay.desafio.contacts.data.repository

import com.picpay.desafio.common.model.Cacheable
import com.picpay.desafio.contacts.data.datasource.local.UserLocalDataSource
import com.picpay.desafio.contacts.data.datasource.remote.UserRemoteDataSource
import com.picpay.desafio.contacts.domain.model.User
import com.picpay.desafio.contacts.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date

internal class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource,
) : UserRepository {
    override suspend fun getUsers(): Flow<Cacheable<List<User>>> = flow {
        val localResult = localDataSource.getUsers()
        if (localResult.isNotEmpty()) {
            emit(
                Cacheable(
                    cache = true,
                    lastModification = Date(localResult.first().lastUpdate),
                    data = localResult.map { it.toDomain() }
                )
            )
        }
        val remoteResult = remoteDataSource.getUsers().map { it.toDomain() }
        emit(
            Cacheable(
                cache = false,
                lastModification = Date(),
                data = remoteResult
            )
        )

        localDataSource.deleteAll()
        localDataSource.saveUsers(remoteResult.map { it.toLocal() })
    }
}