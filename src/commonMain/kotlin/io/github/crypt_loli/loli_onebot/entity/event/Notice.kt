package io.github.crypt_loli.loli_onebot.entity.event

import kotlinx.serialization.SerialName

open class OneBotNoticeEvent: OneBotEvent()

/**
 * 戳一戳事件
 *
 * @param groupId 群组Id
 * @param userId 发送者Id
 * @param targetId 被戳者Id
 */
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