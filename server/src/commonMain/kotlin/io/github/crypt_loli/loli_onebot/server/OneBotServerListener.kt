package io.github.crypt_loli.loli_onebot.server

import io.github.crypt_loli.loli_onebot.server.action.ActionResponse
import kotlinx.serialization.json.JsonObject

interface OneBotServerListener {
    suspend fun onAppOnline(api: OneBotConnection) {}
    suspend fun onAppOffline(api: OneBotConnection, reason: Throwable? = null) {}

    suspend fun onAction(api: OneBotConnection, action: String, params: JsonObject): ActionResponse
}
