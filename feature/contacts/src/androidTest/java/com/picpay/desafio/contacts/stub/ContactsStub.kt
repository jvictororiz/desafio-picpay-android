package com.picpay.desafio.contacts.stub

import com.picpay.desafio.contacts.ui.viewModel.model.ContactUi
import com.picpay.desafio.contacts.ui.viewModel.model.ContactsUiState

internal class ContactsStub {
    fun getSuccessWithoutCache() = ContactsUiState(
        showLoading = false,
        isCache = false,
        dateLastModification = "Atualizado: 01/01/2025 às 17:00",
        listContacts = getContacts()
    )

    fun getSuccessWithCache() = ContactsUiState(
        showLoading = false,
        isCache = true,
        dateLastModification = "Atualizado: 01/01/2025 às 17:00",
        listContacts = getContacts()
    )

    fun getLoading() = ContactsUiState(
        showLoading = true,
        isCache = false,
        dateLastModification = "",
        listContacts = emptyList()
    )


    private fun getContacts() = listOf(
        ContactUi(
            id = "1",
            "",
            "@marcelo",
            name = "Marcelo da silva"
        ),
        ContactUi(
            id = "2",
            "",
            "@pedro",
            name = "Pedro da silva"
        ),
    )
}