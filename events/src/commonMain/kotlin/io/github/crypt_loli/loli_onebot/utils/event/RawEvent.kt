package io.github.crypt_loli.loli_onebot.utils.event

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

class RawEvent(val wrapped: JsonObject) {
    operator fun get(key: String) = wrapped[key]?.jsonPrimitive?.content
}