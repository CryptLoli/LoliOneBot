package io.github.crypt_loli.loli_onebot

import io.github.crypt_loli.loli_onebot.entity.api.ApiBase
import io.github.crypt_loli.loli_onebot.entity.api.ResponseBase
import io.github.crypt_loli.loli_onebot.module.WSSend
import io.github.crypt_loli.loli_onebot.utils.OneBotMessageHandler
import io.github.crypt_loli.loli_onebot.utils.jsonSend
import kotlinx.coroutines.withTimeout
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.uuid.Uuid

class OneBotApi(
    val send: WSSend,
    val handler: OneBotMessageHandler
) {

    suspend inline fun <reified T: ApiBase> send(entity: T) {
        send.send(jsonSend.encodeToString<T>(entity))
    }

    suspend inline fun <reified T: ApiBase> sendWaiting(entity: T, echo: String = Uuid.random().toString(), timeout: Duration = 1.minutes): ResponseBase {
        var entity = jsonSend.encodeToJsonElement<T>(entity).jsonObject
        val deferred = handler.requests.create(echo)

        entity = JsonObject(entity + ("echo" to JsonPrimitive(echo)))

        send.send(jsonSend.encodeToString(entity))

        return withTimeout(timeout) { deferred.await() }
    }

}