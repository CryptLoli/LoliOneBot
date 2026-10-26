package io.github.crypt_loli.loli_onebot.entity.array.type

import io.github.crypt_loli.loli_onebot.entity.array.ArrayEntity
import io.github.crypt_loli.loli_onebot.entity.array.ArrayEntityFactor
import io.github.crypt_loli.loli_onebot.entity.array.OneBotMessage
import io.github.crypt_loli.loli_onebot.utils.jsonSend
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.encodeToJsonElement

@Serializable
data class TextEntity(
    val text: String
): ArrayEntity() {
    override var baseType = factor.type
    override fun encode() = jsonSend.encodeToJsonElement(this)
    companion object {
        val factor = ArrayEntityFactor("text", serializer())
        fun OneBotMessage.appendText(text: Any) = add(TextEntity(text.toString()))
    }
}