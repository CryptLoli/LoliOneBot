package io.github.crypt_loli.loli_onebot.events

import io.github.crypt_loli.loli_onebot.entity.event.OneBotEvent
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
 */
@Serializable
abstract class OneBotNoticeRevokeEvent: OneBotNoticeEvent() {
    abstract val messageId: Long

    /**
     * 私聊消息撤回事件
     *
     * @param messageId 消息唯一标识
     * @param senderId 消息发送者
     */
    @Serializable
    data class Private(
        @SerialName("message_id")
        override val messageId: Long = 0L,
        @SerialName("user_id")
        val senderId: Long
    ): OneBotNoticeRevokeEvent()

    /**
     * 群消息撤回事件
     *
     * @param messageId 消息唯一标识
     * @param groupId 群组
     * @param senderId 消息发送者
     * @param operatorId 消息撤回者 (可能是发送者也可能是群管理员)
     */
    @Serializable
    data class Group(
        @SerialName("message_id")
        override val messageId: Long = 0L,
        @SerialName("group_id")
        val groupId: Long,
        @SerialName("user_id")
        val senderId: Long,
        @SerialName("operator_id")
        val operatorId: Long
    ): OneBotNoticeRevokeEvent()
}

/**
 * 群成员变动时间
 */
@Serializable
abstract class OneBotNoticeGroupMemberEvent: OneBotNoticeEvent() {
    abstract val groupId: Long
    abstract val operatorId: Long
    abstract val userId: Long

    /**
     * 群成员入群事件
     *
     * @param groupId 群组
     * @param operatorId 操作者, 被踢出时为踢出的管理员, 主动退出时为退群者自身
     * @param userId 退群者
     */
    @Serializable
    data class Join(
        @SerialName("sub_type")
        val type: Type,
        @SerialName("group_id")
        override val groupId: Long,
        @SerialName("operator_id")
        override val operatorId: Long,
        @SerialName("user_id")
        override val userId: Long
    ): OneBotNoticeGroupMemberEvent() {
        @Serializable
        enum class Type {
            /**
             * 管理员已同意入群
             */
            @SerialName("approve")
            Approve,
            /**
             * 管理员邀请入群
             */
            @SerialName("invite")
            Invite
        }
    }

    /**
     * 群成员退群事件
     *
     * @param groupId 群组
     * @param operatorId 操作者, 被踢出时为踢出的管理员, 主动退出时为退群者自身
     * @param userId 退群者
     */
    @Serializable
    data class Leave(
        @SerialName("sub_type")
        val type: Type,
        @SerialName("group_id")
        override val groupId: Long,
        @SerialName("operator_id")
        override val operatorId: Long,
        @SerialName("user_id")
        override val userId: Long
    ): OneBotNoticeGroupMemberEvent() {
        @Serializable
        enum class Type {
            @SerialName("leave")
            Leave,
            @SerialName("kick")
            Kick,
            @SerialName("kick_me")
            KickMe
        }
    }
}