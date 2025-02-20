package com.picpay.desafio.contacts.di

import com.picpay.desafio.contacts.data.datasource.local.UserLocalDataSource
import com.picpay.desafio.contacts.data.datasource.local.UserLocalDataSourceImpl
import com.picpay.desafio.contacts.data.datasource.remote.UserRemoteDataSource
import com.picpay.desafio.contacts.data.datasource.remote.UserRemoteDataSourceImpl
import com.picpay.desafio.contacts.data.api.UserApi
import com.picpay.desafio.contacts.data.repository.UserRepositoryImpl
import com.picpay.desafio.contacts.domain.repository.UserRepository
import com.picpay.desafio.contacts.domain.usecase.UserUseCase
import com.picpay.desafio.contacts.domain.usecase.UserUseCaseImpl
import com.picpay.desafio.contacts.ui.viewModel.ContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

internal val featureContacts = module {

    single {
        get<Retrofit>().create(UserApi::class.java)
    }

    factory<UserRemoteDataSource> {
        UserRemoteDataSourceImpl(userApi = get())
    }

    factory<UserLocalDataSource> {
        UserLocalDataSourceImpl(userDao = get())
    }

    factory<UserRepository> {
        UserRepositoryImpl(remoteDataSource = get(), localDataSource = get())
    }

    factory<UserUseCase> {
        UserUseCaseImpl(userRepository = get())
    }

    viewModel {
        ContactsViewModel(userUseCase = get(), application = get())
    }
}