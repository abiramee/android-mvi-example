package com.abiramee.meetmax.auth.presentation

import androidx.compose.runtime.Immutable
import com.abiramee.meetmax.auth.presentation.models.AuthUI

@Immutable
data class AuthState(
    val screen: AuthUI,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)