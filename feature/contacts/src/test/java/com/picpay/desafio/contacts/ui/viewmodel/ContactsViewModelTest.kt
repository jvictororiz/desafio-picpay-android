package com.picpay.desafio.contacts.ui.viewmodel

import android.app.Application
import app.cash.turbine.test
import com.picpay.desafio.common.exception.DefaultException
import com.picpay.desafio.common.exception.NetworkConnectionException
import com.picpay.desafio.common.extension.toText
import com.picpay.desafio.contacts.domain.usecase.UserUseCase
import com.picpay.desafio.contacts.stub.getSuccesStub
import com.picpay.desafio.contacts.ui.viewModel.ContactsViewModel
import com.picpay.desafio.contacts.ui.viewModel.model.ContactUi
import com.picpay.desafio.contacts.ui.viewModel.model.ContactsEvent
import com.picpay.desafio.contacts.ui.viewModel.model.ContactsIntent
import com.picpay.desafio.contacts.ui.viewModel.model.ContactsUiState
import common.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import java.util.Date
import com.picpay.desafio.contacts.R

class ContactsViewModelTest {

    @get:Rule
    var coroutinesTestRule = MainDispatcherRule()

    private val useCase: UserUseCase = mockk(relaxed = true)
    private val application: Application = mockk(relaxed = true)
    private var viewModel: ContactsViewModel = ContactsViewModel(useCase, application)


    @Test
    fun `when RefreshContacts, get all contacts without cache`() = runTest {
        val date = Date()
        val expectedTextTime = "Atualizado: "
        val expectedTime = date.toText("dd/MM/yyyy 'às' HH:mm:ss")
        val expectedList = getSuccesStub(lastModificadion = date, isCache = false)
        coEvery { useCase.getUsers() } returns flow { emit(expectedList) }
        every { application.getString(R.string.last_update, expectedTime) } returns expectedTextTime + expectedTime

        viewModel.dispatchIntent(ContactsIntent.RefreshContacts)

        viewModel.state.test {
            assertEquals(
                ContactsUiState(
                    showLoading = false,
                    isCache = false,
                    dateLastModification = expectedTextTime + expectedTime,
                    listContacts = expectedList.data.map {
                        ContactUi(
                            id = it.id,
                            urlImage = it.image,
                            username = it.username,
                            name = it.name
                        )
                    }
                ),
                awaitItem()
            )
        }
    }



    @Test
    fun `when RefreshContacts, get all contacts with cache`() = runTest {
        val date = Date()
        val expectedTextTime = "Atualizado: "
        val expectedTime = date.toText("dd/MM/yyyy 'às' HH:mm:ss")
        val expectedList = getSuccesStub(lastModificadion = date, isCache = true)
        coEvery { useCase.getUsers() } returns flow { emit(expectedList) }
        every { application.getString(R.string.last_update, expectedTime) } returns expectedTextTime + expectedTime

        viewModel.dispatchIntent(ContactsIntent.RefreshContacts)

        viewModel.state.test {
            assertEquals(
                ContactsUiState(
                    showLoading = false,
                    isCache = true,
                    dateLastModification = expectedTextTime + expectedTime,
                    listContacts = expectedList.data.map {
                        ContactUi(
                            id = it.id,
                            urlImage = it.image,
                            username = it.username,
                            name = it.name
                        )
                    }
                ),
                awaitItem()
            )
        }
    }


    @Test
    fun `when RefreshContacts without connection, show message error`() = runTest {
        val expectedMessageError = "Falha na conexão"
        coEvery { useCase.getUsers() } returns flow { throw NetworkConnectionException() }
        every { application.getString(R.string.connection_error) } returns expectedMessageError

        viewModel.dispatchIntent(ContactsIntent.RefreshContacts)

        viewModel.state.test {
            assertEquals(
                ContactsUiState(showLoading = false),
                awaitItem()
            )
        }
        viewModel.event.test {
            assertEquals(
                ContactsEvent.ShowErrorMessage(expectedMessageError),
                awaitItem()
            )
        }
    }


    @Test
    fun `when RefreshContacts with default error, show message error`() = runTest {
        val expectedMessageError = "Falha padrão"
        coEvery { useCase.getUsers() } returns flow { throw DefaultException("") }
        every {  application.getString(R.string.default_error)} returns expectedMessageError


        viewModel.dispatchIntent(ContactsIntent.RefreshContacts)

        viewModel.state.test {
            assertEquals(
                ContactsUiState(showLoading = false),
                awaitItem()
            )
        }
        viewModel.event.test {
            assertEquals(
                ContactsEvent.ShowErrorMessage(expectedMessageError),
                awaitItem()
            )
        }
    }
}