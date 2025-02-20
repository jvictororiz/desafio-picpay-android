package com.picpay.desafio.persistence.di

import com.picpay.desafio.persistence.RoomBuilder
import com.picpay.desafio.persistence.database.AppDatabase
import com.picpay.desafio.persistence.migrations.Migrations
import com.picpay.desafio.persistence.migrations.v2.MigrationV1toV2
import org.koin.dsl.module

val persistenceModule = module {
    single { RoomBuilder.getRoomBuilder(get(), getAll()) }
    single { get<AppDatabase>().userDao() }

    factory<Migrations> { MigrationV1toV2() }
}