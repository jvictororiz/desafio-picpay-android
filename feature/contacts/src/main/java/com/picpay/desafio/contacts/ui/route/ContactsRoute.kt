package com.picpay.desafio.contacts.ui.route

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.picpay.desafio.contacts.ui.screen.ContactsScreen
import com.picpay.desafio.contacts.ui.viewModel.ContactsViewModel
import com.picpay.desafio.contacts.ui.viewModel.model.ContactsEvent
import com.picpay.desafio.contacts.ui.viewModel.model.ContactsIntent
import com.picpay.desafio.designsystem.theme.PicpayTheme
import org.koin.androidx.compose.koinViewModel


internal const val ContactsRoute = "contacts-route"

@Composable
internal fun ContactsRoute(
    viewModel: ContactsViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val uiEvent by viewModel.event.collectAsStateWithLifecycle()


    LaunchedEffect(uiEvent) {
        when (val event = uiEvent) {
            ContactsEvent.Nothing -> {}
            is ContactsEvent.ShowErrorMessage -> {
                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.resetEvent()
    }


    PicpayTheme {
        ContactsScreen(
            uiState = uiState,
            onRefresh = {
                viewModel.dispatchIntent(ContactsIntent.RefreshContacts)
            }
        )
    }
}