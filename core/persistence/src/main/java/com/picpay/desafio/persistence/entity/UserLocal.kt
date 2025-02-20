package com.picpay.desafio.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "USER")
data class UserLocal(
    @PrimaryKey
    val id: String,
    @ColumnInfo("image")
    val image: String?,
    @ColumnInfo("name")
    val name: String?,
    @ColumnInfo("username")
    val username: String?,
    @ColumnInfo("lastUpdate")
    val lastUpdate: Long = Date().time
)