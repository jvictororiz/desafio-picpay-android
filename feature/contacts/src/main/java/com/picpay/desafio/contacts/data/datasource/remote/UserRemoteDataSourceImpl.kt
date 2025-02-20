package com.picpay.desafio.contacts.data.datasource.remote

import com.picpay.desafio.common.exception.DefaultException
import com.picpay.desafio.common.exception.NetworkConnectionException
import com.picpay.desafio.contacts.data.model.UserRemote
import com.picpay.desafio.contacts.data.api.UserApi
import okio.IOException

internal class UserRemoteDataSourceImpl(
    private val userApi: UserApi,
) : UserRemoteDataSource {
    override suspend fun getUsers(): List<UserRemote> {
        try {
            return userApi.getUsers()
        } catch (exception: Exception) {
            when (exception) {
                is IOException -> throw NetworkConnectionException()
                else -> throw DefaultException(exception.message)
            }
        }
    }
}