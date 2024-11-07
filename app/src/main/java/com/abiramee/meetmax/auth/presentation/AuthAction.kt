package com.abiramee.meetmax.auth.presentation

import com.abiramee.meetmax.auth.domain.ForgotPasswordModel
import com.abiramee.meetmax.auth.domain.SignInModel
import com.abiramee.meetmax.auth.domain.SignUpModel

sealed interface AuthAction {
    data object OnNavigateSignUpClick: AuthAction
    data object OnNavigateSignInClick: AuthAction
    data object OnNavigateForgotPasswordClick: AuthAction
    data class OnSignUpClick(val signUpModel: SignUpModel): AuthAction
    data class  OnSignInClick(val signInModel: SignInModel): AuthAction
    data class  OnForgotPasswordSentClick(val forgotPasswordModel: ForgotPasswordModel): AuthAction
}