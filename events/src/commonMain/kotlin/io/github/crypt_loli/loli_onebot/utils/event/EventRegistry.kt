package io.github.crypt_loli.loli_onebot.utils.event

import io.github.crypt_loli.loli_onebot.OneBotApi
import io.github.crypt_loli.loli_onebot.entity.event.OneBotEvent
import io.github.crypt_loli.loli_onebot.events.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JsonObject

class EventRegistry {
    val events = hashSetOf<EventInfo<out OneBotEvent>>()

    init {
        register(OneBotPrivateMessageEvent.serializer()) { entity -> entity["post_type"] == "message" && entity["message_type"] == "private" }
        register(OneBotGroupMessageEvent.serializer()) { entity -> entity["post_type"] == "message" && entity["message_type"] == "group" }
        register(OneBotRequestBeAddFriend.serializer()) { entity -> entity["post_type"] == "request" && entity["request_type"] == "friend" }
        register(OneBotRequestJoinGroup.serializer()) { entity -> entity["post_type"] == "request" && entity["sub_type"] == "add" }
        register(OneBotRequestBeInviteGroup.serializer()) { entity -> entity["post_type"] == "request" && entity["sub_type"] == "invite" }
        register(OneBotNoticePrivateRevokeEvent.serializer()) { entity -> entity["post_type"] == "notice" && entity["notice_type"] == "friend_recall" }
        register(OneBotNoticeGroupRevokeEvent.serializer()) { entity -> entity["post_type"] == "notice" && entity["notice_type"] == "group_recall" }
        register(OneBotNoticePokeEvent.serializer()) { entity -> entity["post_type"] == "notice" && entity["sub_type"] == "poke" }
    }

    fun register(serializer: KSerializer<out OneBotEvent>, check: (RawEvent) -> Boolean) {
        events.add(EventInfo(check, serializer))
    }

    fun parse(raw: RawEvent, api: OneBotApi? = null) = events.firstNotNullOfOrNull { it.parse(raw) }
        ?.also { entity -> api?.also { api -> entity.api = api } }
    fun parse(raw: JsonObject, api: OneBotApi? = null) = parse(RawEvent(raw), api)
}