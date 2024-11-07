package com.abiramee.meetmax.auth.data.mapper

import com.abiramee.meetmax.auth.data.networking.dto.SignUpBody
import com.abiramee.meetmax.auth.domain.SignUpModel

fun SignUpModel.toSignUpBody(): SignUpBody {
    return SignUpBody(
        name = name,
        email = email,
        password = password,
        dateOfBirth = dateOfBirth,
        gender = gender,
    )
}