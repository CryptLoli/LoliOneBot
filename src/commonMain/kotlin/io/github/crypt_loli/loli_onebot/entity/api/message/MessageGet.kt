package io.github.crypt_loli.loli_onebot.entity.api.message

import io.github.crypt_loli.loli_onebot.entity.api.ApiBase
import io.github.crypt_loli.loli_onebot.entity.array.ArrayMessage
import io.github.crypt_loli.loli_onebot.entity.base.MessageType
import io.github.crypt_loli.loli_onebot.utils.serializer.InstantLongSSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class ApiMessageGet(
    val params: Params,
    val action: String = "get_msg"
): ApiBase() {
    @Serializable
    data class Params(
        @SerialName("message_id")
        val groupId: Long
    )
}

@Serializable
data class ApiResponseMessageGet(
    @Serializable(with = InstantLongSSerializer::class)
    val time: Instant,
    @SerialName("message_type")
    val messageType: MessageType,
    val message: List<ArrayMessage> = emptyList(),
    @SerialName("raw_message")
    val rawMessage: String = "",
    @SerialName("message_id")
    val messageId: Long,
    val sender: Sender,
) {
    @Serializable
    data class Sender(
        @SerialName("user_id")
        val userId: Long,
        val nickname: String = ""
    )
}