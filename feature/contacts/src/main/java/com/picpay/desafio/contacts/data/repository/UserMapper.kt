package com.picpay.desafio.contacts.data.repository

import com.picpay.desafio.contacts.data.model.UserRemote
import com.picpay.desafio.contacts.domain.model.User
import com.picpay.desafio.persistence.room.entity.UserLocal


internal fun UserRemote.toDomain() = User(
    image = image.orEmpty(),
    name = name.orEmpty(),
    id = id.orEmpty(),
    username = username.orEmpty()
)

fun UserLocal.toDomain() = User(
    image = image.orEmpty(),
    name = name.orEmpty(),
    id = id,
    username = username.orEmpty()
)

fun User.toLocal() = UserLocal(
    image = image,
    name = name,
    id = id,
    username = username
)