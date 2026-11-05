package io.github.crypt_loli.loli_onebot.entity.ws

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Request(
    val action: String,
    val params: JsonObject,
    val echo: String? = null
)