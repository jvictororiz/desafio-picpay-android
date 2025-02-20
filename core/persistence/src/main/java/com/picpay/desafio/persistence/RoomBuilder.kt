package com.picpay.desafio.persistence

import android.content.Context
import androidx.room.Room
import com.picpay.desafio.persistence.database.AppDatabase
import com.picpay.desafio.persistence.migrations.Migrations

internal class RoomBuilder {

    companion object {

        fun getRoomBuilder(context: Context, migrations: List<Migrations>): AppDatabase {
            val migrationsRoom = migrations.map { it.migration() }.toTypedArray()

            return Room
                .databaseBuilder(context, AppDatabase::class.java, AppDatabase.NAME)
                .addMigrations(*migrationsRoom)
                .build()
        }
    }
}