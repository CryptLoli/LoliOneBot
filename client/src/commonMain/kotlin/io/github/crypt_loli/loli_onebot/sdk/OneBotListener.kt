package io.github.crypt_loli.loli_onebot.sdk

import io.github.crypt_loli.loli_onebot.entity.event.*
import kotlinx.serialization.json.JsonObject

interface OneBotListener {
    suspend fun onDecodeFail(raw: String, e: Throwable) { }
    suspend fun onRawMessage(raw: JsonObject) { }
    suspend fun onAnyEvent(event: OneBotEvent) { }
}