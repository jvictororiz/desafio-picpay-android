package com.picpay.desafio.persistence.migrations

import androidx.room.migration.Migration

interface Migrations {

    fun migration(): Migration

}