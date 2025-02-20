package com.picpay.desafio.contacts.data.datasource.local

import com.picpay.desafio.persistence.room.entity.UserLocal


internal interface UserLocalDataSource {

    suspend fun getUsers(): List<UserLocal>

    suspend fun saveUsers(users: List<UserLocal>)

    suspend fun deleteAll()
}