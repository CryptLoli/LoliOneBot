package io.github.crypt_loli.loli_onebot.utils

import io.github.crypt_loli.loli_onebot.OneBotApi
import io.github.crypt_loli.loli_onebot.entity.api.ResponseBase
import io.github.crypt_loli.loli_onebot.entity.event.OneBotEvent
import io.github.crypt_loli.loli_onebot.entity.event.OneBotPostType
import io.github.crypt_loli.loli_onebot.entity.event.message.OneBotGroupMessageEvent
import io.github.crypt_loli.loli_onebot.entity.event.message.OneBotPrivateMessageEvent
import io.github.crypt_loli.loli_onebot.entity.event.request.OneBotRequestBeAddFriend
import io.github.crypt_loli.loli_onebot.entity.event.request.OneBotRequestBeInviteGroup
import io.github.crypt_loli.loli_onebot.entity.event.request.OneBotRequestJoinGroup
import io.github.crypt_loli.loli_onebot.sdk.OneBotListener
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class OneBotMessageHandler(
    var listener: OneBotListener,
    val requests: SuspendedRequests
) {

    suspend fun handleEvent(api: OneBotApi, text: String) {
        val element = jsonReceive.parseToJsonElement(text).jsonObject

        if (element.containsKey("retcode")) {
            // API 响应
            val base = jsonReceive.decodeFromJsonElement<ResponseBase>(element)
            val echo = base.echo ?: return

            requests.storage[echo]?.complete(base)
        } else {
            // 下发事件
            val base = jsonReceive.decodeFromJsonElement<OneBotEvent>(element)

            when (base.postType) {
                OneBotPostType.Message -> {
                    when (element["message_type"]?.jsonPrimitive?.content) {
                        "private" -> listener.onPrivateMessage(decode<OneBotPrivateMessageEvent>(api, element))
                        "group" -> listener.onGroupMessage(decode<OneBotGroupMessageEvent>(api, element))
                        else -> error("未知 message_type")
                    }
                }
                OneBotPostType.Request -> {
                    when (element["request_type"]?.jsonPrimitive?.content) {
                        "friend" -> listener.onRequestBeAddFriend(decode<OneBotRequestBeAddFriend>(api, element))
                    }
                    when (element["sub_type"]?.jsonPrimitive?.content) {
                        "add" -> listener.onRequestJoinGroup(decode<OneBotRequestJoinGroup>(api, element))
                        "invite" -> listener.onRequestBeInviteGroup(decode<OneBotRequestBeInviteGroup>(api, element))
                    }
                }
                else -> {}
            }
        }
    }

    inline fun <reified T: OneBotEvent> decode(api: OneBotApi, element: JsonElement) = jsonReceive.decodeFromJsonElement<T>(element)
        .apply { this.api = api }

}