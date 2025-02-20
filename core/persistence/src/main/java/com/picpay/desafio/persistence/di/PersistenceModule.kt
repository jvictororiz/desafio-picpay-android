package com.picpay.desafio.persistence.di

import com.picpay.desafio.persistence.database.AppDatabase
import com.picpay.desafio.persistence.room.RoomBuilder
import org.koin.dsl.module

val persistenceModule = module {
    single { RoomBuilder.getRoomBuilder(get()) }

    single { get<AppDatabase>().userDao() }
}