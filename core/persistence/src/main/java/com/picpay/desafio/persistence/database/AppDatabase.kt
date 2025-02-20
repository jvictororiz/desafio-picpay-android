package com.picpay.desafio.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.persistence.entity.UserLocal
import com.picpay.desafio.persistence.dao.UserDao

@Database(entities = [UserLocal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val NAME = "app_database"
    }
}