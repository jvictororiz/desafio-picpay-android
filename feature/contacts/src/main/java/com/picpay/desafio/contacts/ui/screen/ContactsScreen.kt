package com.picpay.desafio.contacts.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.picpay.desafio.contacts.R
import com.picpay.desafio.contacts.ui.component.ContactItem
import com.picpay.desafio.contacts.ui.viewModel.model.ContactUi
import com.picpay.desafio.contacts.ui.viewModel.model.ContactsUiState
import com.picpay.desafio.designsystem.component.LoadingBox
import com.picpay.desafio.designsystem.theme.PicpayTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ContactsScreen(
    uiState: ContactsUiState,
    onRefresh: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val refreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        modifier = Modifier
            .fillMaxSize(),
        state = refreshState,
        isRefreshing = false,
        onRefresh = {
            onRefresh()
            coroutineScope.launch {
                refreshState.animateToHidden()
            }
        }
    ) {
        LoadingBox(
            modifier = Modifier.testTag("loading"),
            isLoading = uiState.showLoading
        ) {
            Scaffold { paddingValues ->
                LazyColumn(
                    modifier = Modifier.padding(paddingValues)
                ) {
                    item {
                        Text(
                            modifier = Modifier.padding(
                                top = 48.dp,
                                start = 24.dp,
                                bottom = if (uiState.isCache) 0.dp else 32.dp
                            ),
                            text = stringResource(R.string.contacts),
                            style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onSurface)
                        )
                    }

                    if (uiState.isCache) {
                        item {
                            Text(
                                modifier = Modifier
                                    .padding(start = 24.dp, top = 16.dp, bottom = 32.dp),
                                text = uiState.dateLastModification,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.onSurface.copy(
                                        alpha = 0.5f
                                    )
                                )
                            )
                        }
                    }

                    items(uiState.listContacts.size) { index ->
                        val item = uiState.listContacts[index]
                        ContactItem(
                            modifier = Modifier
                                .padding(start = 24.dp)
                                .fillParentMaxWidth(),
                            contact = item
                        )
                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )
                    }
                }
            }
        }
    }

}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
internal fun ContactsScreenNightPreview() {
    PicpayTheme {
        ContactsScreen(
            ContactsUiState(
                showLoading = false,
                isCache = false,
                dateLastModification = "Atualizado: 14/04/2025 às 17:54",
                listContacts = listOf(
                    ContactUi("", "", "@joaovictor", "João Victor"),
                    ContactUi("", "", "@joaovictor2", "João Marcelo"),
                    ContactUi("", "", "@joaovictor3", "João Pedro"),
                )
            ),
            onRefresh = {}
        )
    }
}

@Composable
@Preview
internal fun ContactsScreenLightPreview() {
    PicpayTheme {
        ContactsScreen(
            ContactsUiState(
                showLoading = false,
                isCache = false,
                dateLastModification = "Atualizado: 14/04/2025 às 17:54",
                listContacts = listOf(
                    ContactUi("", "", "@joaovictor", "João Victor"),
                    ContactUi("", "", "@joaovictor2", "João Marcelo"),
                    ContactUi("", "", "@joaovictor3", "João Pedro"),
                )
            ),
            onRefresh = {}
        )
    }
}