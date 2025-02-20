package com.picpay.desafio.contacts.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.picpay.desafio.contacts.di.featureContacts
import com.picpay.desafio.contacts.ui.route.ContactsRoute
import com.picpay.desafio.designsystem.theme.PicpayTheme
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

internal class ContactsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        loadKoinModules(featureContacts)
        setContent {
            val navController = rememberNavController()

            PicpayTheme {
                NavHost(
                    navController = navController,
                    startDestination = ContactsRoute
                ) {
                    composable(ContactsRoute) {
                        ContactsRoute()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(featureContacts)
    }
}