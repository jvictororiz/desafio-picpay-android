package com.picpay.desafio.contacts.navigation

import android.content.Context
import android.content.Intent
import com.picpay.desafio.contacts.ui.ContactsActivity
import com.picpay.desafio.navigation.ContactsNavigation

class ContactsExternalNavigationImpl : ContactsNavigation {

    override fun navigate(context: Context) {
        context.startActivity(Intent(context, ContactsActivity::class.java))
    }
}