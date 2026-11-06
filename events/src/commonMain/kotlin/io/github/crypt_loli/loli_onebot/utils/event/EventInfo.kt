package io.github.crypt_loli.loli_onebot.utils.event

import io.github.crypt_loli.loli_onebot.entity.event.OneBotEvent
import io.github.crypt_loli.loli_onebot.utils.jsonReceive
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JsonObject

class EventInfo<T: OneBotEvent>(
    val check: (RawEvent) -> Boolean,
    val serializer: KSerializer<T>
) {
    fun parse(raw: RawEvent) = if (check(raw)) jsonReceive.decodeFromJsonElement(serializer, raw.wrapped) else null
}