package com.picpay.desafio.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.picpay.desafio.persistence.entity.UserLocal

@Dao
interface UserDao {
    @Insert
    suspend fun insertAll(user: List<UserLocal>)

    @Query("DELETE FROM USER")
    suspend fun deleteAll()

    @Query("SELECT * FROM USER")
    suspend fun getUsers(): List<UserLocal>
}