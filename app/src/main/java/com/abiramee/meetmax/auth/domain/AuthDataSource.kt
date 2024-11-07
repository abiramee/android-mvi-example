package com.abiramee.meetmax.auth.domain

import com.abiramee.meetmax.auth.presentation.models.AuthUI
import com.abiramee.meetmax.core.domain.util.NetworkError
import com.abiramee.meetmax.core.domain.util.Result

interface AuthDataSource {
    suspend fun signIn(signInModel: SignInModel): Result<Boolean, NetworkError>
    suspend fun signUp(signUpModel: SignUpModel): Result<Boolean, NetworkError>
    suspend fun forgotPassword(forgotPasswordModel: ForgotPasswordModel): Result<Boolean, NetworkError>
}