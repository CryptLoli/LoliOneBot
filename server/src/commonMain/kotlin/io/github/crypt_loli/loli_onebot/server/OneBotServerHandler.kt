package io.github.crypt_loli.loli_onebot.server

import io.github.crypt_loli.loli_onebot.entity.ws.Request
import io.github.crypt_loli.loli_onebot.entity.ws.StatusCode
import io.github.crypt_loli.loli_onebot.server.action.ActionResponseFailed
import io.github.crypt_loli.loli_onebot.server.action.ActionResponseOverException
import io.github.crypt_loli.loli_onebot.utils.jsonReceive

class OneBotServerHandler(
    var listener: OneBotServerListener
) {

    suspend fun handleRaw(api: OneBotConnection, raw: String) {
        val request = jsonReceive.decodeFromString<Request>(raw)

        runCatching {
            listener.onAction(api, request.action, request.params)
        }
            .fold(
                onSuccess = { it },
                onFailure = { e ->
                    when (e) {
                        is ActionResponseOverException -> e.data
                        else -> ActionResponseFailed(code = StatusCode.InternalServerError, message = e.message ?: "Unknown error")
                    }
                }
            )
            .also { data ->
                api.push(data.warp().encodeToString(request))
            }
    }
}
