package com.picpay.desafio.contacts.robot

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText

internal class ContactsRobot(
    private val composeTestRule: ComposeContentTestRule
) {

    fun startScreen(composable: @Composable () -> Unit) = apply {
        composeTestRule.setContent {
            composable()
        }
    }

    fun verifyTitle() = apply {
        composeTestRule.onNodeWithText("Contatos").assertExists()
    }

    fun verifyIfThereIsNoCache() = apply {
        composeTestRule.onNodeWithText("Atualizado: 01/01/2025 às 17:00").assertDoesNotExist()
    }

    fun verifyIfHasCache() = apply {
        composeTestRule.onNodeWithText("Atualizado: 01/01/2025 às 17:00").assertExists()
    }

    fun verifyNameContacts() = apply {
        composeTestRule.onNodeWithText("Marcelo da silva").assertExists()
        composeTestRule.onNodeWithText("Pedro da silva").assertExists()
    }

    fun verifyUsernameContacts() = apply {
        composeTestRule.onNodeWithText("@marcelo").assertExists()
        composeTestRule.onNodeWithText("@pedro").assertExists()
    }

    fun verifyShowingLoading() = apply {
        composeTestRule.onNodeWithTag("loading").assertExists()
    }
}