package io.github.crypt_loli.loli_onebot.utils.event

import io.github.crypt_loli.loli_onebot.entity.event.OneBotEvent
import io.github.crypt_loli.loli_onebot.utils.jsonReceive
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonPrimitive
import kotlin.reflect.KClass

class EventInfo<T: OneBotEvent>(
    val clazz: KClass<T>,
    val serializer: KSerializer<T>,
    val keys: Map<String, String>
) {
    val keysAsJsonObject by lazy { JsonObject(keys.map { it.key to JsonPrimitive(it.value) }.toMap()) }

    fun check(raw: JsonObject) = keys.all { (key, value) -> raw[key]?.jsonPrimitive?.content == value  }
    fun parse(raw: JsonObject) = if (check(raw)) jsonReceive.decodeFromJsonElement(serializer, raw) else null
}