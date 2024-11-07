package com.abiramee.meetmax.auth.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class SignUpBody(
    val name: String,
    val email: String,
    val password: String,
    val dateOfBirth: String,
    val gender: String,
)
