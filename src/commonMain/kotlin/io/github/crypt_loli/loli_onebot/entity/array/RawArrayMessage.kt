package io.github.crypt_loli.loli_onebot.entity.array

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull

@Serializable
data class RawArrayMessage(
    val type: String,
    val data: JsonElement = JsonNull
)