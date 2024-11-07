package com.abiramee.meetmax.auth.presentation.models

sealed class AuthUI {
    data class SignUp(val title1: String = "Getting Started", val title2: String = "Create an account to continue and connect with the people."): AuthUI()
    data class SignIn(val title1: String = "Sign In", val title2: String = "Welcome back, you’ve been missed!"): AuthUI()
    data class ForgotPassword(val title1: String = "Forgot password?", val title2: String = "Forgot password?"): AuthUI()
}