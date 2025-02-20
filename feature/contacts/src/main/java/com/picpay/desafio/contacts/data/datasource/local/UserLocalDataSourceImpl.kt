package com.picpay.desafio.contacts.data.datasource.local

import com.picpay.desafio.persistence.dao.UserDao
import com.picpay.desafio.persistence.entity.UserLocal


internal class UserLocalDataSourceImpl(
    private val userDao: UserDao
) : UserLocalDataSource {
    override suspend fun getUsers(): List<UserLocal> {
        return userDao.getUsers()
    }

    override suspend fun saveUsers(users: List<UserLocal>) {
        userDao.insertAll(users)
    }

    override suspend fun deleteAll() {
        userDao.deleteAll()
    }
}