package com.picpay.desafio.contacts.stub

import com.picpay.desafio.common.model.Cacheable
import com.picpay.desafio.contacts.domain.model.User
import java.util.Date


fun getSuccesStub(
    isCache: Boolean = false,
    lastModificadion: Date = Date(),
) = Cacheable(
    cache = isCache,
    lastModification = lastModificadion,
    data = listOf(
        User("", "Marcelo", "1", "@marcelo"),
        User("", "Pedro", "2", "@pedro"),
        User("", "João", "3", "@joao"),
        User("", "Victor", "4", "@victor"),
    )
)