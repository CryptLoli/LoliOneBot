package io.github.crypt_loli.loli_onebot.entity.array.type

import io.github.crypt_loli.loli_onebot.entity.array.ArrayEntity
import io.github.crypt_loli.loli_onebot.entity.array.ArrayEntityFactor
import io.github.crypt_loli.loli_onebot.utils.jsonSend
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.encodeToJsonElement

@Serializable
data class ShareEntity(
    val url: String,
    val title: String = "",
    val content: String = "",
    @SerialName("image")
    val imageUrl: String = "",
): ArrayEntity() {
    override var baseType = factor.type
    override fun encode() = jsonSend.encodeToJsonElement(this)
    companion object {
        val factor = ArrayEntityFactor("share", serializer(), exactly = true)
    }
}