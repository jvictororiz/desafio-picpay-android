package com.picpay.desafio.contacts.data.api

import com.picpay.desafio.contacts.data.model.UserRemote
import retrofit2.http.GET

internal interface UserApi {
    @GET("users")
    suspend fun getUsers(): List<UserRemote>
}