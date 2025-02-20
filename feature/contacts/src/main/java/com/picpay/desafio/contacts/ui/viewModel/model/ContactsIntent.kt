package com.picpay.desafio.contacts.ui.viewModel.model

internal sealed class ContactsIntent {

    data object RefreshContacts : ContactsIntent()
}