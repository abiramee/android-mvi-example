package com.abiramee.meetmax.core.data.network

import com.abiramee.meetmax.core.domain.util.NetworkError
import com.abiramee.meetmax.core.domain.util.Result
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import java.nio.channels.UnresolvedAddressException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, NetworkError> {
    val response = try {
        execute()
    } catch(e: UnresolvedAddressException) {
        return com.abiramee.meetmax.core.domain.util.Result.Error(NetworkError.NO_INTERNET)
    } catch(e: SerializationException) {
        return com.abiramee.meetmax.core.domain.util.Result.Error(NetworkError.SERIALIZATION)
    } catch(e: Exception) {
        coroutineContext.ensureActive()
        return com.abiramee.meetmax.core.domain.util.Result.Error(NetworkError.UNKNOWN)
    }

    return responseToResult(response)
}