package com.picpay.desafio.contacts.ui.viewModel.model

internal data class ContactsUiState(
    val showLoading: Boolean = false,
    val isCache: Boolean = false,
    val dateLastModification: String = "",
    val listContacts: List<ContactUi> = emptyList()
)

internal data class ContactUi(
    val id: String,
    val urlImage: String,
    val username: String,
    val name: String,
)