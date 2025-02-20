package com.picpay.desafio.api.di

import com.picpay.desafio.api.retrofit.RetrofitConfig
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

class RetrofitModule {

    companion object {
        fun getModule(baseUrl: String) = module {

            single<OkHttpClient> {
                RetrofitConfig.client()
            }

            single<Retrofit> {
                RetrofitConfig.retrofit(baseUrl, get<OkHttpClient>())
            }
        }
    }
}
