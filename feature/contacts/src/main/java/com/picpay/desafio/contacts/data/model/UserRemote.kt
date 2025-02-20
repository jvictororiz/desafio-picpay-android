package com.picpay.desafio.contacts.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

internal data class UserRemote(
    @SerializedName("img")
    val image: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("username")
    val username: String?
): Serializable