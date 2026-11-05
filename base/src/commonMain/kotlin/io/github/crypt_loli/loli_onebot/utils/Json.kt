package io.github.crypt_loli.loli_onebot.utils

import kotlinx.serialization.json.Json

val jsonReceive = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

val jsonSend = Json {
    encodeDefaults = true
}