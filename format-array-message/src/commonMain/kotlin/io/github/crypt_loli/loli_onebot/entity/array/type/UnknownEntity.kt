package io.github.crypt_loli.loli_onebot.entity.array.type

import io.github.crypt_loli.loli_onebot.entity.array.ArrayEntity
import kotlinx.serialization.json.JsonElement

data class UnknownEntity(
    override var baseType: String,
    val data: JsonElement
): ArrayEntity() {
    override fun encode() = data
}