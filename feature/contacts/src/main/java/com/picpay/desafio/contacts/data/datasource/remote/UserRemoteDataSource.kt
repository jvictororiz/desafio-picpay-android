package com.picpay.desafio.contacts.data.datasource.remote

import com.picpay.desafio.contacts.data.model.UserRemote

internal interface UserRemoteDataSource {

    suspend fun getUsers(): List<UserRemote>
}