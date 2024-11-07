package com.abiramee.meetmax.di

import com.abiramee.meetmax.auth.data.networking.RemoteAuthDataSource
import com.abiramee.meetmax.auth.domain.AuthDataSource
import com.abiramee.meetmax.auth.presentation.AuthViewmodel
import com.abiramee.meetmax.core.data.network.HttpClientFactory
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind


val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteAuthDataSource).bind<AuthDataSource>()
    viewModelOf(::AuthViewmodel)
}