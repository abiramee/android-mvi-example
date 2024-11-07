package com.abiramee.meetmax.auth.presentation

sealed interface AuthShare {
    data class ToastError(val message: String): AuthShare
    data object Navigation: AuthShare
}