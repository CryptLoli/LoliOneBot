package io.github.crypt_loli.loli_onebot.entity.array.type

import io.github.crypt_loli.loli_onebot.entity.array.ArrayEntity
import io.github.crypt_loli.loli_onebot.entity.array.ArrayEntityFactor
import io.github.crypt_loli.loli_onebot.utils.jsonSend
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.encodeToJsonElement

@Serializable
data class LocationEntity(
    val lat: Float,
    val lon: Float
): ArrayEntity() {
    override var baseType = factor.type
    override fun encode() = jsonSend.encodeToJsonElement(this)
    companion object {
        val factor = ArrayEntityFactor("location", serializer())
    }
}