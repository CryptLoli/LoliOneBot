package io.github.crypt_loli.loli_onebot.events

import io.github.crypt_loli.loli_onebot.entity.event.OneBotEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
abstract class OneBotRequestEvent: OneBotEvent() {
    abstract val flag: String
}

/**
 * 被添加好友事件
 *
 * @param userId 对方账号
 * @param comment 验证信息
 */
@Serializable
data class OneBotRequestBeAddFriend(
    override val flag: String,
    @SerialName("user_id")
    val userId: Long,
    val comment: String
): OneBotRequestEvent()

/**
 * 被邀请入群事件
 *
 * @param groupId 群号
 * @param invitorId 邀请者账号
 */
@Serializable
data class OneBotRequestBeInviteGroup(
    override val flag: String,
    @SerialName("group_id")
    val groupId: Long,
    @SerialName("user_id")
    val invitorId: Long
): OneBotRequestEvent()

/**
 * 用户入群申请 (Bot为管理员处理申请)
 *
 * @param groupId 群号
 * @param userId 申请者账号
 * @param comment 申请者填写的理由(如果有)
 */
@Serializable
data class OneBotRequestJoinGroup(
    override val flag: String,
    @SerialName("user_id")
    val userId: Long,
    @SerialName("group_id")
    val groupId: Long,
    val comment: String = ""
): OneBotRequestEvent()
