package io.github.crypt_loli.loli_onebot.entity.api.message

import io.github.crypt_loli.loli_onebot.entity.api.ApiBase
import io.github.crypt_loli.loli_onebot.entity.array.ArrayMessage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiMessageGroupRaw(
    val params: Params
): ApiBase("send_group_msg") {
    @Serializable
    data class Params(
        @SerialName("group_id")
        val groupId: Long,
        val message: String
    )
}

@Serializable
data class ApiMessageGroupArray(
    val params: Params
): ApiBase("send_group_msg") {
    @Serializable
    data class Params(
        @SerialName("group_id")
        val groupId: Long,
        val message: List<ArrayMessage>
    )
}

@Serializable
data class ApiMessagePrivateRaw(
    val params: Params
): ApiBase("send_private_msg") {
    @Serializable
    data class Params(
        @SerialName("user_id")
        val userId: Long,
        val message: String
    )
}

@Serializable
data class ApiMessagePrivateArray(
    val params: Params
): ApiBase("send_private_msg") {
    @Serializable
    data class Params(
        @SerialName("user_id")
        val userId: Long,
        val message: List<ArrayMessage>
    )
}

@Serializable
data class ApiResponseMessageSend(
    @SerialName("message_id")
    val id: Long,
)