package io.github.crypt_loli.loli_onebot

import io.github.crypt_loli.loli_onebot.entity.api.ApiBase
import io.github.crypt_loli.loli_onebot.entity.api.ResponseBase
import io.github.crypt_loli.loli_onebot.module.WSSend
import io.github.crypt_loli.loli_onebot.utils.OneBotMessageHandler
import io.github.crypt_loli.loli_onebot.utils.jsonSend
import kotlinx.coroutines.withTimeout
import kotlinx.serialization.json.JsonElement
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

    suspend inline fun <reified T: ApiBase> send(entity: T, extra: Map<String, JsonElement>? = null) {
        var json = jsonSend.encodeToJsonElement<T>(entity).jsonObject
        json = JsonObject( json + ("action" to JsonPrimitive(entity.action)))
        extra?.also { extra -> json = JsonObject(json + extra) }

        send.send(jsonSend.encodeToString(json))
    }

    suspend inline fun <reified T: ApiBase> sendWaiting(entity: T, echo: String = Uuid.random().toString(), timeout: Duration = 1.minutes): ResponseBase {
        val deferred = handler.requests.create(echo)

        send(entity, mapOf("echo" to JsonPrimitive(echo)))

        return withTimeout(timeout) { deferred.await() }
    }

}