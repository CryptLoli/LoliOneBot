package io.github.crypt_loli.loli_onebot.utils

import io.github.crypt_loli.loli_onebot.OneBotClientApi
import io.github.crypt_loli.loli_onebot.entity.ws.Response
import io.github.crypt_loli.loli_onebot.sdk.OneBotListener
import io.github.crypt_loli.loli_onebot.utils.event.EventRegistry
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject

class OneBotMessageHandler(
    var listener: OneBotListener,
    val requests: SuspendedRequests,
    val eventRegistry: EventRegistry = EventRegistry(),
) {

    suspend fun handleRaw(api: OneBotClientApi, text: String) {
        runCatching {
            val element = jsonReceive.parseToJsonElement(text).jsonObject

            listener.onRawMessage(element)

            if (element.containsKey("retcode")) {
                // API 响应
                handleApiResponse(jsonReceive.decodeFromJsonElement<Response>(element))
            } else {
                // 下发事件
                handleEvent(api, element)
            }
        }
            .onFailure { e -> listener.onDecodeFail(text, e) }
    }

    fun handleApiResponse(respond: Response) {
        val echo = respond.echo ?: return

        requests.complete(echo, respond)
    }

    suspend fun handleEvent(api: OneBotClientApi, element: JsonObject) {
        val event = eventRegistry.parse(element, api) ?: return
        listener.onAnyEvent(event)
    }

}