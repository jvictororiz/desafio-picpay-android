package com.picpay.desafio.persistence.room

import android.content.Context
import androidx.room.Room
import com.picpay.desafio.persistence.database.AppDatabase

internal class RoomBuilder {

    companion object {

        fun getRoomBuilder(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.NAME)
                .build()
        }
    }
}