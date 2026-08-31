package io.github.crypt_loli.loli_onebot.entity.api.group

import io.github.crypt_loli.loli_onebot.entity.api.ApiBase
import io.github.crypt_loli.loli_onebot.utils.serializer.InstantLongSSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant


@Serializable
data class ApiGroupEssenceMessageList(
    val params: Params,
    val action: String = "get_essence_msg_list"
): ApiBase() {
    @Serializable
    data class Params(
        @SerialName("group_id")
        val groupId: Long
    )
}



@Serializable
data class ApiGroupEssenceMessageDelete(
    val params: Params,
    val action: String = "delete_essence_msg"
): ApiBase() {
    @Serializable
    data class Params(
        @SerialName("message_id")
        val messageId: Long
    )
}

@Serializable
data class ApiGroupEssenceMessageSet(
    val params: Params,
    val action: String = "set_essence_msg"
): ApiBase() {
    @Serializable
    data class Params(
        @SerialName("message_id")
        val messageId: Long
    )
}

/**
 * 群精华消息
 *
 * @param senderId 消息发送者账号
 * @param senderNick 消息发送者昵称
 * @param senderAt 消息发送时间
 * @param operatorId 操作者账号 (aka. 设置此消息为精华消息的群管理员账号)
 * @param operatorNick 操作者昵称
 * @param operatorAt 操作时间
 * @param messageId 消息Id
 */
@Serializable
data class ApiResponseGroupEssenceMessage(
    @SerialName("sender_id")
    val senderId: Long,
    @SerialName("sender_nick")
    val senderNick: String,
    @SerialName("sender_time")
    @Serializable(with = InstantLongSSerializer::class)
    val senderAt: Instant,
    @SerialName("operator_id")
    val operatorId: Long,
    @SerialName("operator_nick")
    val operatorNick: String,
    @SerialName("operator_time")
    @Serializable(with = InstantLongSSerializer::class)
    val operatorAt: Instant,
    @SerialName("message_id")
    val messageId: Long
)