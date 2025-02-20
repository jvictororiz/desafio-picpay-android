package com.picpay.desafio.contacts.ui.viewModel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.common.exception.NetworkConnectionException
import com.picpay.desafio.common.extension.toText
import com.picpay.desafio.common.model.Cacheable
import com.picpay.desafio.common.model.dispatcher.DispatcherProvider
import com.picpay.desafio.common.base.BaseViewModel
import com.picpay.desafio.contacts.R
import com.picpay.desafio.contacts.domain.model.User
import com.picpay.desafio.contacts.domain.usecase.UserUseCase
import com.picpay.desafio.contacts.ui.viewModel.model.ContactUi
import com.picpay.desafio.contacts.ui.viewModel.model.ContactsEvent
import com.picpay.desafio.contacts.ui.viewModel.model.ContactsIntent
import com.picpay.desafio.contacts.ui.viewModel.model.ContactsUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val patternLastModification = "dd/MM/yyyy 'às' HH:mm:ss"

internal class ContactsViewModel(
    private val userUseCase: UserUseCase,
    private val application: Application,
) : BaseViewModel<ContactsIntent, ContactsEvent, ContactsUiState>(
    ContactsUiState(),
    ContactsEvent.Nothing
) {

    init {
        dispatchIntent(ContactsIntent.RefreshContacts)
    }

    override fun dispatchIntent(intent: ContactsIntent) {
        when (intent) {
            ContactsIntent.RefreshContacts -> refreshContacts()
        }
    }

    private fun refreshContacts() {
        uiState.update { it.copy(showLoading = true) }

        viewModelScope.launch(DispatcherProvider.IO) {
            userUseCase.getUsers()
                .catch { error ->
                    setupErrorMessage(error)
                }
                .collectLatest { result ->
                    setupSuccess(result)
                }
        }
    }

    private fun setupSuccess(result: Cacheable<List<User>>) {
        uiState.update {
            it.copy(
                showLoading = false,
                isCache = result.cache,
                dateLastModification = application.getString(
                    R.string.last_update,
                    result.lastModification.toText(
                        patternLastModification
                    )
                ),
                listContacts = result.data.map { item ->
                    ContactUi(
                        item.id,
                        urlImage = item.image,
                        username = item.username,
                        name = item.name
                    )
                }
            )
        }
    }

    private fun setupErrorMessage(error: Throwable) {
        uiState.update { it.copy(showLoading = false) }
        val messageError = when (error) {
            is NetworkConnectionException -> application.getString(R.string.connection_error)
            else -> application.getString(R.string.default_error)
        }
        uiEvent.update {
            ContactsEvent.ShowErrorMessage(messageError)
        }
    }
}