package io.github.crypt_loli.loli_onebot.entity.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

open class OneBotNoticeEvent: OneBotEvent()

/**
 * 戳一戳事件
 *
 * @param groupId 群组Id
 * @param userId 发送者Id
 * @param targetId 被戳者Id
 */
@Serializable
data class OneBotNoticePokeEvent(
    @SerialName("group_id")
    val groupId: Long? = null,
    @SerialName("user_id")
    val userId: Long,
    @SerialName("target_id")
    val targetId: Long
): OneBotNoticeEvent() {
    val isGroup
        get() = groupId != null
}

/**
 * 消息撤回事件
 *
 * @param messageId 消息唯一标识
 */
@Serializable
open class OneBotNoticeRevokeEvent(
    @SerialName("message_id")
    val messageId: Long = 0L
): OneBotNoticeEvent()

/**
 * 私聊消息撤回事件
 *
 * @param senderId 消息发送者
 */
@Serializable
data class OneBotNoticePrivateRevokeEvent(
    @SerialName("user_id")
    val senderId: Long
): OneBotNoticeRevokeEvent()

/**
 * 群消息撤回事件
 *
 * @param groupId 群组
 * @param senderId 消息发送者
 * @param operatorId 消息撤回者 (可能是发送者也可能是群管理员)
 */
@Serializable
data class OneBotNoticeGroupRevokeEvent(
    @SerialName("group_id")
    val groupId: Long,
    @SerialName("user_id")
    val senderId: Long,
    @SerialName("operator_id")
    val operatorId: Long
): OneBotNoticeRevokeEvent()