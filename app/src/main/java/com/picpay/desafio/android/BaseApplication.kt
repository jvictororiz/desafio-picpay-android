package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.api.di.RetrofitModule
import com.picpay.desafio.contacts.navigation.ContactsExternalNavigationImpl
import com.picpay.desafio.navigation.ContactsNavigation
import com.picpay.desafio.persistence.di.persistenceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(
                module { factory<ContactsNavigation> { ContactsExternalNavigationImpl() } },
                RetrofitModule.getModule(BuildConfig.BASE_URL),
                persistenceModule
            )
        }
    }
}