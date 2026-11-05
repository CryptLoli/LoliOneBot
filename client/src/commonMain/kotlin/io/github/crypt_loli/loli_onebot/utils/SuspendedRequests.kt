package io.github.crypt_loli.loli_onebot.utils

import io.github.crypt_loli.loli_onebot.entity.ws.Response
import kotlinx.coroutines.CompletableDeferred
import kotlin.uuid.Uuid

class SuspendedRequests {

    val storage = hashMapOf<String, CompletableDeferred<Response>>()

    fun create(echo: String = Uuid.random().toString()) = CompletableDeferred<Response>()
        .also { storage[echo] = it }

    fun complete(echo: String, data: Response) = storage
        .remove(echo)
        ?.complete(data)

    fun cancelAll() = storage.values
        .onEach { it.cancel() }
        .clear()

}