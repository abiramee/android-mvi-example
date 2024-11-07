package com.abiramee.meetmax.core.data.network

import com.abiramee.meetmax.core.domain.util.NetworkError
import com.abiramee.meetmax.core.domain.util.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> responseToResult(response: HttpResponse): Result<T, NetworkError> {
    return when(response.status.value) {
        in 200..299 -> {
            try {
                com.abiramee.meetmax.core.domain.util.Result.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                com.abiramee.meetmax.core.domain.util.Result.Error(NetworkError.SERIALIZATION)
            }
        }
        408 -> {
            com.abiramee.meetmax.core.domain.util.Result.Error(NetworkError.REQUEST_TIMEOUT)
        }
        428 -> {
            com.abiramee.meetmax.core.domain.util.Result.Error(NetworkError.TOO_MANY_REQUEST)
        }
        in 500..599 -> {
            com.abiramee.meetmax.core.domain.util.Result.Error(NetworkError.SERVER_ERROR)
        }
        else -> com.abiramee.meetmax.core.domain.util.Result.Error(NetworkError.SERVER_ERROR)
    }
}