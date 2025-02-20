package com.picpay.desafio.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.picpay.desafio.navigation.ContactsNavigation
import org.koin.android.ext.android.inject


internal class MainActivity : ComponentActivity() {

    private val contactsNavigation : ContactsNavigation by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactsNavigation.navigate(this)
        finish()
    }
}