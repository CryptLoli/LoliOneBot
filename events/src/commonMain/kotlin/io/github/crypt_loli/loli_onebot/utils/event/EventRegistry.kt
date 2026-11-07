package io.github.crypt_loli.loli_onebot.utils.event

import io.github.crypt_loli.loli_onebot.OneBotApi
import io.github.crypt_loli.loli_onebot.entity.Account
import io.github.crypt_loli.loli_onebot.entity.event.OneBotEvent
import io.github.crypt_loli.loli_onebot.events.*
import io.github.crypt_loli.loli_onebot.utils.jsonSend
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import kotlin.reflect.KClass
import kotlin.time.Clock
import kotlin.time.Instant

class EventRegistry {
    val events = hashMapOf<KClass<out OneBotEvent>, EventInfo<out OneBotEvent>>()

    init {
        register(OneBotPrivateMessageEvent.serializer(), "post_type" to "message", "message_type" to "private")
        register(OneBotGroupMessageEvent.serializer(), "post_type" to "message", "message_type" to "group")
        register(OneBotRequestBeAddFriend.serializer(), "post_type" to "request", "request_type" to "friend")
        register(OneBotRequestJoinGroup.serializer(), "post_type" to "request", "sub_type" to "add")
        register(OneBotRequestBeInviteGroup.serializer(), "post_type" to "request", "sub_type" to "invite")
        register(OneBotNoticePrivateRevokeEvent.serializer(), "post_type" to "notice", "notice_type" to "friend_recall")
        register(OneBotNoticeGroupRevokeEvent.serializer(), "post_type" to "notice", "notice_type" to "group_recall")
        register(OneBotNoticePokeEvent.serializer(), "post_type" to "notice", "sub_type" to "poke")
    }

    inline fun <reified T: OneBotEvent> register(serializer: KSerializer<T>, vararg keys: Pair<String, String>) {
        events[T::class] = EventInfo(T::class, serializer, keys.toMap())
    }
    fun parse(raw: JsonObject, api: OneBotApi? = null) = events.values
        .firstNotNullOfOrNull { it.parse(raw) }
        ?.also { entity -> api?.also { api -> entity.api = api } }

    inline fun <reified Event: OneBotEvent> encode(entity: Event, account: Account): JsonObject {
        if (entity.time == Instant.DISTANT_PAST) entity.time = Clock.System.now()
        if (entity.selfId == 0L) entity.selfId = account.id

        return JsonObject(
            content = requireNotNull(events[entity::class]) { "类型 ${entity::class} 不在已知事件表里" }.keysAsJsonObject +
                    jsonSend.encodeToJsonElement<Event>(entity).jsonObject
        )
    }

}