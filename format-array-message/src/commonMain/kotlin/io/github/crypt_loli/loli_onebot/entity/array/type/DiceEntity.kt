package io.github.crypt_loli.loli_onebot.entity.array.type

import io.github.crypt_loli.loli_onebot.entity.array.ArrayEntity
import io.github.crypt_loli.loli_onebot.entity.array.ArrayEntityFactor
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.JsonObject

@Serializable
class DiceEntity: ArrayEntity() {
    @Transient
    override var baseType = factor.type
    override fun encode() = JsonObject(emptyMap())
    companion object {
        val factor = ArrayEntityFactor("dice", serializer())
    }
}