package com.abiramee.meetmax.auth.data.networking

import com.abiramee.meetmax.auth.data.mapper.toSignUpBody
import com.abiramee.meetmax.auth.domain.AuthDataSource
import com.abiramee.meetmax.auth.domain.ForgotPasswordModel
import com.abiramee.meetmax.auth.domain.SignInModel
import com.abiramee.meetmax.auth.domain.SignUpModel
import com.abiramee.meetmax.core.data.network.constructUrl
import com.abiramee.meetmax.core.data.network.safeCall
import com.abiramee.meetmax.core.domain.util.NetworkError
import com.abiramee.meetmax.core.domain.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.delay

class RemoteAuthDataSource(private val httpClient: HttpClient): AuthDataSource {
    override suspend fun signIn(signInModel: SignInModel): Result<Boolean, NetworkError> {
        delay(2000L)
        return Result.Success(true)
    }

    override suspend fun signUp(signUpModel: SignUpModel): Result<Boolean, NetworkError> {
        delay(2000L)
        return Result.Success(true)
    }

    override suspend fun forgotPassword(forgotPasswordModel: ForgotPasswordModel): Result<Boolean, NetworkError> {
        delay(2000L)
        return Result.Success(true)
    }

}