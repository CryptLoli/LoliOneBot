package io.github.crypt_loli.loli_onebot.entity.base

import io.github.crypt_loli.loli_onebot.utils.serializer.InstantLongSSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

/**
 * 群聊用户信息
 *
 * @param userId 账号
 * @param nickname 全局昵称
 *
 * @param groupId 所在群
 * @param card 群名片
 * @param age 年龄
 * @param area 地区
 * @param joinTime 加群于
 * @param lastSentTime 最后一次发言于
 * @param level 群聊等级
 * @param role 身份标签 (群主/管理员/成员)
 * @param sex 性别
 * @param title 专属头衔
 */
@Serializable
class GroupMemberInfo(
    @SerialName("user_id")
    val userId: Long,
    val nickname: String,

    @SerialName("group_id")
    val groupId: Long,
    val card: String = "",
    val age: Int = 0,
    val area: String? = null,
    @SerialName("join_time")
    @Serializable(with = InstantLongSSerializer::class)
    val joinTime: Instant = Instant.DISTANT_PAST,
    @SerialName("last_sent_time")
    @Serializable(with = InstantLongSSerializer::class)
    val lastSentTime: Instant = Instant.DISTANT_PAST,
    val level: String = "",
    val role: GroupRole,
    val sex: UserSex = UserSex.Unknown,
    val title: String? = null
)