package com.picpay.desafio.contacts

import androidx.compose.ui.test.junit4.createComposeRule
import com.picpay.desafio.contacts.robot.ContactsRobot
import com.picpay.desafio.contacts.stub.ContactsStub
import com.picpay.desafio.contacts.ui.screen.ContactsScreen
import org.junit.Rule
import org.junit.Test

internal class ContactsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val robot = ContactsRobot(composeTestRule)
    private val stub = ContactsStub()

    @Test
    fun whenStartScreenReturnsContactsWithoutCache() {
        val uiState = stub.getSuccessWithoutCache()

        robot.startScreen {
            ContactsScreen(
                uiState = uiState,
                onRefresh = {}
            )
        }.verifyTitle()
            .verifyNameContacts()
            .verifyIfThereIsNoCache()
            .verifyUsernameContacts()
    }

    @Test
    fun whenScrollToEndShowLastItem() {
        val uiState = stub.getSuccessWithoutCache()

        robot.startScreen {
            ContactsScreen(
                uiState = uiState,
                onRefresh = {}
            )
        }.verifyTitle()
            .verifyNameContacts()
            .verifyIfThereIsNoCache()
            .verifyUsernameContacts()
    }

    @Test
    fun whenStartScreenReturnsContactsWithCache() {
        val uiState = stub.getSuccessWithCache()

        robot.startScreen {
            ContactsScreen(
                uiState = uiState,
                onRefresh = {}
            )
        }.verifyTitle()
            .verifyNameContacts()
            .verifyIfHasCache()
            .verifyUsernameContacts()
    }

    @Test
    fun whenLoadingShowProgressIndicator() {
        val uiState = stub.getLoading()

        robot.startScreen {
            ContactsScreen(
                uiState = uiState,
                onRefresh = {}
            )
        }.verifyTitle()
            .verifyShowingLoading()
    }
}