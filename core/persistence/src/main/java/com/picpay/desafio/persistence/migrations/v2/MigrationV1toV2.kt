package com.picpay.desafio.persistence.migrations.v2

import androidx.room.migration.Migration
import com.picpay.desafio.persistence.migrations.Migrations

class MigrationV1toV2 : Migrations {
    override fun migration(): Migration {
        return Migration(1, 2) {
        }
    }
}