package com.picpay.desafio.common.model

import java.util.Date

data class Cacheable<T>(
    val cache: Boolean,
    val lastModification: Date,
    val data : T
)