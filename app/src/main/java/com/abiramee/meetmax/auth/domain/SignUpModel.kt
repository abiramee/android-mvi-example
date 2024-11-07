package com.abiramee.meetmax.auth.domain

data class SignUpModel(
    val name: String,
    val email: String,
    val password: String,
    var dateOfBirth: String,
    val gender: String,
)
