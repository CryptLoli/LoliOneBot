package io.github.crypt_loli.loli_onebot.utils

import io.github.crypt_loli.loli_onebot.entity.api.ResponseBase
import kotlinx.coroutines.CompletableDeferred
import kotlin.uuid.Uuid

class SuspendedRequests {

    val storage = hashMapOf<String, CompletableDeferred<ResponseBase>>()

    fun create(echo: String = Uuid.random().toString()) = CompletableDeferred<ResponseBase>()
        .also { storage[echo] = it }

    fun remove(echo: String) = storage.remove(echo)

    fun complete(echo: String, data: ResponseBase) {
        storage[echo]?.complete(data)
    }

}