package com.picpay.desafio.persistence.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.picpay.desafio.persistence.room.entity.UserLocal

@Dao
interface UserDao {
    @Insert
    suspend fun insertAll(user: List<UserLocal>)

    @Query("DELETE FROM USER")
    suspend fun deleteAll()

    @Query("SELECT * FROM USER")
    suspend fun getUsers(): List<UserLocal>
}