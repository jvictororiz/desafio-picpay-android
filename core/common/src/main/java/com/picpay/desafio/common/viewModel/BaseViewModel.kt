package com.picpay.desafio.common.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<Intent, Event, State>(
    initialState: State,
    private val initialEvent: Event
) : ViewModel() {

    protected val uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state: StateFlow<State> = uiState.asStateFlow()

    protected val uiEvent: MutableStateFlow<Event> = MutableStateFlow(initialEvent)
    val event: StateFlow<Event> = uiEvent.asStateFlow()

    abstract fun dispatchIntent(intent: Intent)

    fun resetEvent() {
        uiEvent.value = initialEvent
    }
}