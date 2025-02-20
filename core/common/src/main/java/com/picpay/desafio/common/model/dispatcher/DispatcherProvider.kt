package com.picpay.desafio.common.model.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object DispatcherProvider {
    var Main: CoroutineDispatcher = Dispatchers.Main
    var IO: CoroutineDispatcher = Dispatchers.IO
    var Default: CoroutineDispatcher = Dispatchers.Default
}