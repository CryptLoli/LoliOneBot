package io.github.crypt_loli.loli_onebot.entity.array

import kotlinx.serialization.KSerializer

class ArrayEntityFactor<T>(
    val type: String,
    val serializer: KSerializer<T>
)