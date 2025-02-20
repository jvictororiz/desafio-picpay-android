package com.picpay.desafio.contacts.ui.viewModel.model

internal sealed class ContactsEvent {
    data class ShowErrorMessage(val message: String) : ContactsEvent()
    data object Nothing : ContactsEvent()
}